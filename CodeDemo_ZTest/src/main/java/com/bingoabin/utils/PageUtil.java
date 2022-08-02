package com.bingoabin.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.bingoabin.utils.vo.IdNameVo;
import com.bingoabin.utils.vo.PageList;
import com.bingoabin.utils.vo.QueryVo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PageUtil {
    private PageUtil() {
        super();
    }

    public static <T> Order<Void, T> of(Supplier<List<T>> select) {
        return new Paging<>(e -> select.get());
    }

    public static <E, T> Select<E, T> of(Function<E, List<T>> select) {
        return new Paging<>(select);
    }

    public static <T> PageByListMap<T> list(T[] values) {
        return new PageByList<>(values);
    }

    public interface PageByListMap<T> {
        PageByListQuery<T> map(Function<T, Integer> keyGen, Function<T, String> valGen);
    }

    public interface PageByListQuery<T> {
        PageList<IdNameVo> query(QueryVo queryVo);
    }

    public static class PageByList<T> implements PageByListMap<T>, PageByListQuery<T> {
        private Iterable<T> items;
        private Function<T, Integer> keyGen;
        private Function<T, String> valGen;

        public PageByList(Iterable<T> items) {
            if (items == null) {
                this.items = Collections.emptyList();
                return;
            }
            this.items = items;
        }

        public PageByList(List<T> items) {
            if (items == null) {
                this.items = Collections.emptyList();
                return;
            }
            this.items = items;
        }

        public PageByList(T[] items) {
            if (items == null) {
                this.items = Collections.emptyList();
                return;
            }
            this.items = Arrays.asList(items);
        }

        @Override
        public PageByList<T> map(Function<T, Integer> keyGen, Function<T, String> valGen) {
            this.keyGen = keyGen;
            this.valGen = valGen;
            return this;
        }

        @Override
        public PageList<IdNameVo> query(QueryVo queryVo) {
            String query = queryVo == null || queryVo.getQuery() == null ? "" : queryVo.getQuery();
            List<IdNameVo> list = StreamSupport.stream(items.spliterator(), false)
                    .filter(Objects::nonNull)
                    .map(e -> {
                        Integer key = keyGen.apply(e);
                        String val = valGen.apply(e);
                        return new IdNameVo(key, val);
                    })
                    .filter(e -> e.getId() != null || e.getName() != null)
                    .filter(e -> e.getName().contains(query))
                    .collect(Collectors.toList());
            return PageList.of(list);
        }
    }

    public static interface Select<E, T> {
        Example<E, T> example(E example);
    }

    public static interface Example<E, T> {
        Order<E, T> order(String order);

        default Order<E, T> orderById() {
            return order("id");
        }

        default Order<E, T> noOrder() {
            return order(null);
        }
    }

    public static interface Order<E, T> {
        PageList<T> query(QueryVo queryVo, int maxPage, int defPage);

        default PageList<T> query(QueryVo queryVo) {
            return query(queryVo, PageList.MAX_PAGE, PageList.DEF_PAGE_SIZE);
        }

        default void typeKeep(E t) {
        }
    }

    private static class Paging<E, T> implements Select<E, T>, Example<E, T>, Order<E, T> {
        private Function<E, List<T>> select;
        private E example;
        private String order;

        public Paging(Function<E, List<T>> select) {
            this.select = select;
        }

        @Override
        public Example<E, T> example(E example) {
            Objects.requireNonNull(example);
            this.example = example;
            return this;
        }

        @Override
        public Order<E, T> order(String order) {
            this.order = order;
            return this;
        }


        @Override
        public PageList<T> query(QueryVo queryVo, int maxPage, int defPage) {
            if (queryVo == null) {
                queryVo = new QueryVo();
            }
            Integer pageNo = queryVo.getPageNo();
            if (pageNo == null || pageNo <= 0) {
                pageNo = 1;
            }
            Integer pageSize = queryVo.getPageSize();
            if (pageSize == null) {
                pageSize = defPage;
            } else if (pageSize < 1 || pageSize > maxPage) {
                pageSize = defPage;
            }
            Page<T> pageList = PageMethod.startPage(pageNo, pageSize);
            if (order != null) {
                pageList.setOrderBy(order);
            }
            pageList = pageList.doSelectPage(() -> select.apply(example));
            long total = pageList.getTotal();
            return PageList.of(
                    pageList.getPageSize(),
                    pageList.getPageNum(),
                    Long.valueOf(total).intValue(),
                    pageList);
        }
    }
}
