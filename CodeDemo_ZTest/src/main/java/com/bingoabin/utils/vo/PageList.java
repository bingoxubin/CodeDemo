package com.bingoabin.utils.vo;


import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageList<T> {
    public static final int MAX_PAGE = 1000;
    public static final int DEF_PAGE_SIZE = 100;
    public static final int DEF_PAGE_NO = 1;

    private int pageSize;
    private int pageNo;
    private int total;
    private List<T> items;

    public PageList() {
        super();
    }

    public PageList(int pageSize, int pageNo, int total, List<T> items) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.total = total;
        this.items = items;
    }

    public static <T> PageList<T> empty() {
        return new PageList<>(
                DEF_PAGE_SIZE, DEF_PAGE_NO,
                0, Collections.emptyList());
    }

    public static <T> PageList<T> empty(QueryVo param) {
        if (param == null) {
            return empty();
        }
        return ofNullable(
                param.getPageNo(), param.getPageSize(),
                null, Collections.emptyList());
    }

    public static <T> PageList<T> of(QueryVo param, Integer count, List<T> list) {
        if (param == null) {
            return empty();
        }
        return ofNullable(
                param.getPageNo(), param.getPageSize(),
                count, list);
    }

    public PageList<T> copy(PageList<T> template) {
        if (template == null) {
            return this;
        }
        this.pageSize = template.pageSize;
        this.pageNo = template.pageNo;
        this.total = template.total;
        this.items = template.items;
        return this;
    }

    public static <T, R> PageList<R> map(PageList<T> source, Function<T, R> map) {
        Objects.requireNonNull(map);
        PageList<R> result = new PageList<>();
        if (source == null) {
            result.setPageNo(DEF_PAGE_NO);
            result.setPageSize(DEF_PAGE_SIZE);
            result.setTotal(0);
            return result;
        }
        result.setPageNo(source.getPageNo());
        result.setPageSize(source.getPageSize());
        result.setTotal(source.getTotal());
        if (CollectionUtils.isEmpty(source.getItems())) {
            return result;
        }
        result.setItems(source.getItems().stream().map(map).collect(Collectors.toList()));
        return result;
    }

    public static <T> PageList<T> ofNullable(Integer pageNum, Integer pageSize, Integer total, List<T> list) {
        pageNum = pageNum == null ? DEF_PAGE_NO : pageNum;
        pageSize = pageSize == null ? DEF_PAGE_SIZE : pageSize;
        total = total == null ? (list == null ? 0 : list.size()) : total;
        return of(pageSize, pageNum, total, list);
    }

    public static <T> PageList<T> of(int pageSize, int pageNum, int total, List<T> list) {
        return new PageList(pageSize, pageNum, total, list);
    }

    public static <T> PageList<T> of(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return PageList.empty();
        }
        return new PageList(list.size(), 1, list.size(), list);
    }

    /**
     * List分页
     *
     * @param pageNum
     * @param pageSize
     * @param list
     * @param <T>
     * @return
     */
    public static <T> PageList<T> split(int pageNum, int pageSize, List<T> list) {
        int total = list.size();
        int start = (pageNum - 1) * pageSize;
        if (total <= start) {
            List<T> subList = new ArrayList<>();
            return PageList.of(pageNum, pageSize, total, subList);
        }
        int end = pageNum * pageSize;
        if (end > total) {
            end = total;
        }
        List<T> subList = list.subList(start, end);
        return PageList.of(pageNum, pageSize, total, subList);
    }

    public <R> PageList<R> map(Function<T, R> map) {
        return map(this, map);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
}
