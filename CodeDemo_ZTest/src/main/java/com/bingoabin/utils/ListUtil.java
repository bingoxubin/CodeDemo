package com.bingoabin.utils;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * @author HanJunsheng
 * @date 2021/7/16
 * @copyright sankuai.com
 */
public class ListUtil {
    private ListUtil() {
        super();
    }

    public static <T, R> List<R> toList(T[] ts, Function<T, R> convert) {
        return (ts == null ? Stream.<T>empty() : Arrays.stream(ts)).map(convert).collect(Collectors.toList());
    }


    public static <T> Stream<T> stream(Collection<T> collection) {
        return collection == null ? Stream.empty() : collection.stream();
    }

    public static <T> Stream<T> stream(T[] array) {
        return array == null ? Stream.empty() : Arrays.stream(array);
    }

    public static <T, R> List<R> map(Collection<T> collection, Function<T, R> map) {
        return stream(collection).map(map).collect(Collectors.toList());
    }

    public static <T, R> Map<R, T> toMap(Collection<T> collection, Function<T, R> map) {
        return stream(collection).collect(Collectors.toMap(map, Function.identity()));
    }

    public static <T, R> Map<R, T> toMapSafe(Collection<T> collection, Function<T, R> map) {
        return stream(collection).collect(Collectors.toMap(map, Function.identity(), (a, b) -> a));
    }

    public static <T> List<T> toList(Collection<T> collection) {
        if (collection instanceof List) {
            return (List<T>) collection;
        }
        return new ArrayList<>(collection);
    }

    public static <T> List<T> toList(String str, String regex, Function<String, T> map) {
        return Optional.ofNullable(str)
                .map(s -> s.split(regex))
                .map(Arrays::asList)
                .map(l -> map(l, map))
                .orElse(Collections.emptyList());
    }

    public static <T> void split(List<T> list, int size, OnPage<T> onPage) {
        if (list == null || list.isEmpty() || size < 0) {
            return;
        }
        for (int start = 0; start < list.size(); start += size) {
            int end = Math.min(start + size, list.size());
            onPage.accept(start, end, list.size(), list.subList(start, end));
        }
    }

    public static <T> Consumer<List<T>> splitBuilder(int size, OnPage<T> onPage) {
        return list -> split(list, size, onPage);
    }

    @SafeVarargs
    public static void lazyDone(Supplier<Lazy<?, ?>>... lazies) {
        if (lazies != null && lazies.length > 0) {
            for (Supplier<Lazy<?, ?>> lazyGen : lazies) {
                if (lazyGen == null) {
                    continue;
                }
                Lazy<?, ?> lazy = lazyGen.get();
                if (lazy == null) {
                    continue;
                }
                lazy.done();
            }
        }
    }

    public interface OnPage<T> {
        void accept(int start, int end, int total, List<T> list);
    }

    public static <K, V> Optional<V> inMap(Map<K, V> map, K key) {
        if (map == null || key == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(map.get(key));
    }

    ////lazy
    public static <K, V> Lazy<K, V> lazyOfValue(Supplier<V> v) {
        return lazyOfValue(null, k -> v.get());
    }

    public static <K, V> Lazy<K, V> lazyOfValue(Class<K> kClass, Function<K, V> v) {
        return ListUtil.<K, V>lazy(null, null)
                .valueList(e -> Collections.emptyList())
                .valueKey(vv -> null)
                .defValueGetter(v)
                .create();
    }

    /**
     * lazy query
     *
     * @param list the query which use the keys to get values
     * @param <K>  key
     * @param <V>  val
     * @return builder
     */
    public static <K, V> LazyBuilderKey<K, V> lazyOfList(final Function<List<K>, List<V>> list) {
        return new LazyBuilderHolder<K, V>().valueList(keys -> list.apply(toList(keys)));
    }

    /**
     * lazy query
     *
     * @param keyClass the query which use the keys to get values
     * @param <K>      key
     * @param <V>      val
     * @return builder
     */
    public static <K, V> LazyBuilderType<K, V> lazy(Class<K> keyClass, Class<V> valueClass) {
        return new LazyBuilderHolder<>();
    }

    public static <T> Optional<T> ifOnlyOne(Collection<T> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return Optional.empty();
        }
        if (collection.size() != 1) {
            return Optional.empty();
        }
        return Optional.ofNullable(collection.iterator().next());
    }

    /**
     * lazy type
     *
     * @param <K> key
     * @param <V> val
     */
    public interface LazyBuilderType<K, V> {
        LazyBuilderKey<K, V> valueList(Function<Collection<K>, Collection<V>> list);
    }

    /**
     * generate the key
     *
     * @param <K> key
     * @param <V> val
     */
    public interface LazyBuilderKey<K, V> {
        LazyBuilderDef<K, V> valueKey(Function<V, K> key);
    }

    /**
     * default value getter
     *
     * @param <K> key
     * @param <V> val
     */
    public interface LazyBuilderDef<K, V> {
        LazyBuilder<K, V> defValueGetter(Function<K, V> def);
    }

    /**
     * create an instance
     *
     * @param <K>
     * @param <V>
     */
    public interface LazyBuilder<K, V> {
        Lazy<K, V> create();
    }

    public static <S, T> Lazy<S, T> lazyMapping(
            Function<S, T> mapTo, Function<T, S> mapFrom) {
        return ListUtil.<S, T>lazy(null, null)
                .valueList(e -> ListUtil.map(e, mapTo))
                .valueKey(mapFrom)
                .defValueGetter(FnUtil.getNull())
                .create();
    }

    public static <K1, K2, V2> Lazy<K1, V2> lazyConcat(
            Lazy<K1, K2> lazy1, Lazy<K2, V2> lazy2) {
        return lazyCombine(lazy1, lazy2)
                .genK2(Function.identity())
                .genV(((k1, k2, v2) -> v2))
                .nullHandledInVGen();
    }

    public static <K1, V1, K2, V2, V> LazyCombineK2Gen<K1, V1, K2, V2, V> lazyCombine(
            Lazy<K1, V1> lazy1, Lazy<K2, V2> lazy2, Class<V> vClass) {
        return new LazyCombine<>(lazy1, lazy2, vClass);
    }

    public static <K1, V1, K2, V2> LazyCombineK2Gen<K1, V1, K2, V2, V2> lazyCombine(
            Lazy<K1, V1> lazy1, Lazy<K2, V2> lazy2) {
        return new LazyCombine<K1, V1, K2, V2, V2>(lazy1, lazy2, null);
    }

    public interface LazyCombineK2Gen<K1, V1, K2, V2, V> {
        LazyCombineVGen<K1, V1, K2, V2, V> genK2(Function<V1, K2> k2Gen);
    }

    public interface LazyCombineVGen<K1, V1, K2, V2, V> {
        LazyCombineCreate<K1, V1, K2, V2, V> genV(TriFn<K1, V1, V2, V> vGen);
    }

    public interface LazyCombineCreate<K1, V1, K2, V2, V> {
        Lazy<K1, V> nullHandledInVGen();

        Lazy<K1, V> create(Function<K1, V> def);
    }

    public interface TriFn<K1, K2, K3, V> {
        V apply(K1 k1, K2 k2, K3 k3);
    }

    /**
     * Combine two lazy objects
     *
     * @param <K1> key1
     * @param <V1> val1
     * @param <K2> key2
     * @param <V2> val2
     */
    public static class LazyCombine<K1, V1, K2, V2, V> implements
            LazyCombineK2Gen<K1, V1, K2, V2, V>,
            LazyCombineVGen<K1, V1, K2, V2, V>,
            LazyCombineCreate<K1, V1, K2, V2, V> {
        private final Lazy<K1, V1> lazy1;
        private final Lazy<K2, V2> lazy2;
        private Function<V1, K2> k2Gen;
        private TriFn<K1, V1, V2, V> vGen;


        private LazyCombine(Lazy<K1, V1> lazy1, Lazy<K2, V2> lazy2, Class<V> vClass) {
            this.lazy1 = lazy1;
            this.lazy2 = lazy2;
        }

        @Override
        public LazyCombineVGen<K1, V1, K2, V2, V> genK2(Function<V1, K2> k2Gen) {
            this.k2Gen = k2Gen;
            return this;
        }

        @Override
        public LazyCombineCreate<K1, V1, K2, V2, V> genV(TriFn<K1, V1, V2, V> vGen) {
            this.vGen = vGen;
            return this;
        }

        @Override
        public Lazy<K1, V> nullHandledInVGen() {
            return doCreate(vGen);
        }

        @Override
        public Lazy<K1, V> create(Function<K1, V> def) {
            return doCreate((k1, v1, v2) -> v1 == null || v2 == null ?
                    def.apply(k1) : vGen.apply(k1, v1, v2));
        }


        private Lazy<K1, V> doCreate(TriFn<K1, V1, V2, V> vGenFn) {
            return new Lazy<K1, V>() {
                @Override
                public Lazy<K1, V> set(K1 key, Consumer<V> setter) {
                    lazy1.set(key, v1 -> {
                        K2 k2 = getK2(v1);
                        lazy2.set(k2, v2 -> {
                            V v = vGenFn.apply(key, v1, v2);
                            setter.accept(v);
                        });
                    });
                    return this;
                }

                private K2 getK2(V1 v1) {
                    return v1 == null ? null : k2Gen.apply(v1);
                }

                @Override
                public Optional<V> one(K1 key) {
                    V1 v1 = lazy1.one(key).orElse(null);
                    K2 k2 = getK2(v1);
                    V2 v2 = lazy2.one(k2).orElse(null);
                    return Optional.ofNullable(vGenFn.apply(key, v1, v2));
                }

                @Override
                public void done() {
                    lazy1.done();
                    lazy2.done();
                }
            };
        }
    }

    private static class LazyBuilderHolder<K, V> implements
            LazyBuilderType<K, V>,
            LazyBuilderKey<K, V>,
            LazyBuilderDef<K, V>,
            LazyBuilder<K, V> {
        private Function<V, K> key;
        private Function<Collection<K>, Collection<V>> list;
        private Function<K, V> def;

        @Override
        public Lazy<K, V> create() {
            return new LazyImpl<>(list, key, def);
        }

        @Override
        public LazyBuilderKey<K, V> valueList(Function<Collection<K>, Collection<V>> list) {
            this.list = list;
            return this;
        }

        @Override
        public LazyBuilderDef<K, V> valueKey(Function<V, K> key) {
            this.key = key;
            return this;
        }

        @Override
        public LazyBuilder<K, V> defValueGetter(Function<K, V> def) {
            this.def = def;
            return this;
        }
    }

    public interface Lazy<K, V> {

        /**
         * Setter will be called with the value related to the key
         * <p>
         * Ignored when value not found
         *
         * @param key    key
         * @param setter value is not null
         * @return this
         */
        default Lazy<K, V> add(K key, Consumer<V> setter) {
            return this.add(key, setter, e -> {
            });
        }

        /**
         * Setter will be called with the value related to the key
         * <p>
         * other will be called with the key when value not found
         *
         * @param key    key
         * @param setter value is not null
         * @return this
         */
        default Lazy<K, V> add(K key, Consumer<V> setter, Consumer<K> other) {
            return this.set(key, v -> {
                if (v != null) {
                    setter.accept(v);
                } else {
                    other.accept(key);
                }
            });
        }

        /**
         * Setter will be called with the key and related value
         *
         * @param key    key
         * @param setter value might be null
         * @return this
         */
        Lazy<K, V> set(K key, Consumer<V> setter);

        Optional<V> one(K key);

        default <V2> Lazy<K, V2> map(
                Function<V, V2> map) {
            return this.map(map, e -> null);
        }

        default <V2> Lazy<K, V2> map(
                Function<V, V2> map,
                Function<K, V2> defVal) {
            Lazy<K, V> origin = this;
            return new Lazy<K, V2>() {
                @Override
                public Lazy<K, V2> set(K key, Consumer<V2> setter) {
                    origin.set(key, e -> {
                        V2 v2 = e == null ? defVal.apply(key) : map.apply(e);
                        setter.accept(v2);
                    });
                    return this;
                }

                @Override
                public Optional<V2> one(K key) {
                    return origin.one(key).map(map);
                }

                @Override
                public void done() {
                    origin.done();
                }
            };
        }

        /**
         * Do fetch related value and call setters
         */
        void done();

        default <R> List<R> mapAll(List<K> keys, Function<V, R> fn) {
            return doneToStream(keys).map(fn).collect(Collectors.toList());
        }

        default Stream<V> doneToStream(List<K> keys) {
            return doneToStream(keys, Function.identity(), (k, v) -> v);
        }

        default <X, R> Stream<R> doneToStream(List<X> keys, Function<X, K> mapKey, BiFunction<K, V, R> fn) {
            if (keys == null) {
                return Stream.empty();
            }
            return doneToStream(keys.stream().map(mapKey), fn);

        }

        default <R> Stream<R> doneToStream(Stream<K> stream, BiFunction<K, V, R> fn) {
            return stream.map(k -> {
                        Val<R> r = new Val<>();
                        this.add(k, v -> r.t = fn.apply(k, v));
                        return r;
                    })
                    .collect(collector())
                    .map(e -> e.t);
        }

        default <CT> Collector<CT, Stream<CT>, Stream<CT>> collector() {
            return createCollector(this, Collections.emptySet(), Collection::stream);
        }

        default <CT, CA, CR> Collector<CT, CA, CR> collector(Collector<CT, CA, CR> collector) {
            return createCollector(
                    this, collector.characteristics(),
                    s -> s.stream().collect(collector));
        }
    }

    private static final class Val<T> {
        private T t;
    }

    private static <CT, CA, CR, K, V> Collector<CT, CA, CR> createCollector(
            Lazy<K, V> lazy,
            Set<Collector.Characteristics> characteristics,
            Function<Collection<CT>, CR> finisher) {
        Collection<CT> buffer = newCollection(
                characteristics.contains(Collector.Characteristics.CONCURRENT));
        return new Collector<CT, CA, CR>() {
            @Override
            public Supplier<CA> supplier() {
                return () -> null;
            }

            @Override
            public BiConsumer<CA, CT> accumulator() {
                return (a, t) -> buffer.add(t);
            }

            @Override
            public BinaryOperator<CA> combiner() {
                return (a, b) -> a;
            }

            @Override
            public Function<CA, CR> finisher() {
                return c -> {
                    lazy.done();
                    return finisher.apply(buffer);
                };
            }

            @Override
            public Set<Characteristics> characteristics() {
                return characteristics;
            }
        };
    }


    public static <CT> Collection<CT> newCollection(boolean concurrent) {
        if (concurrent) {
            return new LinkedBlockingQueue<>();
        }
        return new ArrayList<>();
    }

    public static class LazyImpl<K, V> implements Lazy<K, V> {
        private final Function<V, K> key;
        private final MapList<K, Consumer<V>> map;
        private final Function<Collection<K>, Collection<V>> list;
        private final Function<K, V> def;

        public LazyImpl(Function<Collection<K>, Collection<V>> list,
                        Function<V, K> key,
                        Function<K, V> defValue) {
            this.key = key;
            this.list = list;
            this.def = defValue;
            this.map = new MapList<>();
        }

        /**
         * Setter will be called with the value related to the key
         * <p>
         * Ignored when value not found
         *
         * @param key    key
         * @param setter value is not null
         * @return this
         */
        @Override
        public Lazy<K, V> add(K key, Consumer<V> setter) {
            if (key == null) {
                return this;
            }
            map.put(key, v -> {
                if (v != null) {
                    setter.accept(v);
                }
            });
            return this;
        }

        /**
         * Setter will be called with the value related to the key
         * <p>
         * other will be called with the key when value not found
         *
         * @param key    key
         * @param setter value is not null
         * @return this
         */
        @Override
        public Lazy<K, V> add(K key, Consumer<V> setter, Consumer<K> other) {
            map.put(key, v -> {
                if (v != null) {
                    setter.accept(v);
                } else {
                    other.accept(key);
                }
            });
            return this;
        }

        /**
         * Setter will be called with the key and related value
         *
         * @param key    key
         * @param setter value might be null
         * @return this
         */
        public Lazy<K, V> set(K key, Consumer<V> setter) {
            if (key == null) {
                return this;
            }
            map.put(key, setter);
            return this;
        }

        public Optional<V> one(K key) {
            Collection<V> apply = list.apply(Collections.singletonList(key));
            if (CollectionUtils.isEmpty(apply)) {
                return Optional.empty();
            }
            V value = apply.iterator().next();
            return Optional.ofNullable(value);
        }

        /**
         * Do fetch related value and call setters
         */
        public void done() {
            Collection<V> call = map.map.isEmpty() ?
                    Collections.emptyList() : list.apply(map.map.keySet());

            ListUtil.stream(call).forEach(e -> {
                List<Consumer<V>> target = map.map.remove(key.apply(e));
                ListUtil.stream(target).forEach(valueCall -> valueCall.accept(e));
            });

            if (map.map.size() != 0) {
                map.map.forEach((key1, target) -> {
                    V defValue = def.apply(key1);
                    for (Consumer<V> valueCall : target) {
                        valueCall.accept(defValue);
                    }
                });
                map.map.clear();
            }
        }
    }

    public static class MapListBuilder<K, V> {
        private final Function<V, K> key;

        public MapListBuilder(Function<V, K> key) {
            this.key = key;
        }

        public MapList<K, V> build(Collection<V> values) {
            return build(values, Function.identity());
        }

        public <M> MapList<K, V> build(Collection<M> values, Function<M, V> vMap) {
            MapList<K, V> result = new MapList<>();
            if (CollectionUtils.isEmpty(values)) {
                return result;
            }
            for (M value : values) {
                V v = vMap.apply(value);
                result.put(key.apply(v), v);
            }
            return result;
        }
    }

    public static <K, V> MapListBuilder<K, V> mapList(Function<V, K> mapper) {
        return new MapListBuilder<>(mapper);
    }

    public static class MapList<K, V> {

        private final Map<K, List<V>> map;

        public MapList() {
            this.map = new HashMap<>();
        }


        public static <K, V> MapListBuilder<K, V> of(Function<V, K> mapper) {
            return new MapListBuilder<>(mapper);
        }

        public List<V> get(K key) {
            List<V> list = map.computeIfAbsent(key, k -> new LinkedList<>());
            Objects.requireNonNull(list);//keep
            return list;
        }

        public MapList<K, V> put(K key, V value) {
            List<V> list = get(key);
            list.add(value);
            return this;
        }

        public MapList<K, V> putAll(K key, List<V> value) {
            List<V> list = get(key);
            list.addAll(value);
            return this;
        }

        public MapList<K, V> call(K key, Consumer<List<V>> call) {
            List<V> values = get(key);
            call.accept(values);
            return this;
        }

        public MapList<K, V> del(K key, V value) {
            return del(key, value, false);
        }

        public MapList<K, V> del(K key, V value, boolean trimMap) {
            List<V> list = map.get(key);
            if (list != null) {
                list.remove(value);
                if (trimMap && list.isEmpty()) {
                    map.remove(key);
                }
            }
            return this;
        }

        public Map<K, List<V>> getMap() {
            return map;
        }
    }

    public static <T, K>
    Collector<T, ?, MapList<K, T>> toMapList(Function<? super T, ? extends K> keyMapper) {
        return toMapList(keyMapper, Function.identity());
    }

    public static <T, K, U>
    Collector<T, ?, MapList<K, U>> toMapList(Function<? super T, ? extends K> keyMapper,
                                             Function<? super T, ? extends U> valueMapper) {
        return Collector.of(
                MapList::new,
                (m, t) -> m.put(keyMapper.apply(t), valueMapper.apply(t)),
                (m1, m2) -> {
                    m1.getMap().forEach(m2::putAll);
                    return m2;
                },
                Collector.Characteristics.IDENTITY_FINISH

        );
    }

    public static <S, T> LazyFlowMap<S, T> lazyFlow(List<S> sList, Supplier<T> tSupplier) {
        return new LazyFlow<>(sList, tSupplier);
    }

    public interface LazyFlowMap<S, T> {
        LazyFlowDone<S, T> map(LazyFlowFn<S, T> setter);
    }

    public interface LazyFlowDone<S, T> {
        Stream<T> doneStream();

        List<T> done(Comparator<T> sort);

        List<T> done();
    }

    private static class LazyFlow<S, T> implements LazyFlowMap<S, T>, LazyFlowDone<S, T> {
        private final List<S> sList;
        private final Supplier<T> tSupplier;

        private final T[] target;
        private final LazyFlowElem flow;

        private LazyFlowFn<S, T> mapper;

        private LazyFlow(List<S> sList, Supplier<T> tSupplier) {
            this.sList = (sList == null ? Collections.emptyList() : sList);
            this.tSupplier = tSupplier;
            target = (T[]) new Object[this.sList.size()];
            flow = new LazyFlowElem(target);
        }

        @Override
        public LazyFlow<S, T> map(LazyFlowFn<S, T> mapper) {
            this.mapper = mapper;
            return this;
        }

        @Override
        public Stream<T> doneStream() {
            IntStream.range(0, sList.size())
                    .peek(i -> target[i] = tSupplier.get())
                    .forEach(i -> mapper.accept(sList.get(i), target[i], flow.sub(i)));
            flow.done();
            return Arrays.stream(target, 0, sList.size())
                    .filter(Objects::nonNull);
        }

        @Override
        public List<T> done(Comparator<T> sort) {
            Stream<T> tStream = doneStream();
            if (sort != null) {
                tStream = tStream.sorted(sort);
            }
            return tStream.collect(Collectors.toList());
        }

        @Override
        public List<T> done() {
            return done(null);
        }
    }

    public static interface LazySubFlow {
        /**
         * Process all, include null value
         *
         * @param lazy
         * @param k
         * @param consumer
         * @param <K>
         * @param <V>
         * @return
         */
        <K, V> LazySubFlow process(Lazy<K, V> lazy, K k, BiConsumer<V, LazySubFlow> consumer);

        /**
         * Process only non null values
         *
         * @param lazy
         * @param k
         * @param consumer
         * @param <K>
         * @param <V>
         * @return
         */
        default <K, V> LazySubFlow next(Lazy<K, V> lazy, K k, BiConsumer<V, LazySubFlow> consumer) {
            return process(lazy, k, (v, f) -> {
                if (v == null) {
                    return;
                }
                consumer.accept(v, f);
            });
        }

        /**
         * Process only non null values
         *
         * @param lazy
         * @param k
         * @param consumer
         * @param <K>
         * @param <V>
         * @return
         */
        default <K, V> LazySubFlow next(Lazy<K, V> lazy, K k, Consumer<V> consumer) {
            return next(lazy, k, (v, f) -> {
                Objects.requireNonNull(consumer);//keep
                consumer.accept(v);
            });
        }

        /**
         * Process only non null values, and will stop return this value
         *
         * @param lazy
         * @param k
         * @param consumer
         * @param <K>
         * @param <V>
         * @return
         */
        default <K, V> LazySubFlow define(Lazy<K, V> lazy, K k, BiConsumer<V, LazySubFlow> consumer) {
            return process(lazy, k, (v, f) -> {
                if (v == null) {
                    f.stop();
                    return;
                }
                consumer.accept(v, f);
            });
        }

        /**
         * Process only non null values, and will stop return this value
         *
         * @param lazy
         * @param k
         * @param consumer
         * @param <K>
         * @param <V>
         * @return
         */
        default <K, V> LazySubFlow define(Lazy<K, V> lazy, K k, Consumer<V> consumer) {
            return define(lazy, k, (v, f) -> {
                Objects.requireNonNull(consumer);//keep
                consumer.accept(v);
            });
        }

        void stop();
    }

    private static class LazyFlowElem {
        private final Map<Lazy<?, ?>, LazyFlowElem> lazyList = new LinkedHashMap<>();
        private final List<Runnable> initParams = new ArrayList<>();
        private final Object[] target;

        public LazyFlowElem(Object[] target) {
            this.target = target;
        }

        public void add(Lazy<?, ?> lazy, Consumer<LazyFlowElem> runnable) {
            LazyFlowElem flowElem = lazyList.computeIfAbsent(lazy, l -> new LazyFlowElem(target));
            Objects.requireNonNull(flowElem);//keep
            flowElem.initParams.add(() -> runnable.accept(flowElem));
        }

        public void done() {
            lazyList.forEach((l, s) -> {
                s.init();
                l.done();
                s.done();
            });
        }

        public void init() {
            initParams.forEach(Runnable::run);
        }

        public LazySubFlow sub(int i) {
            return new LazySubFlowElem(i, this);
        }

        private boolean stoped(int index) {
            return target[index] == null;
        }

        public void stop(int index) {
            target[index] = null;
        }
    }

    private static class LazySubFlowElem implements LazySubFlow {
        private final int index;
        private final LazyFlowElem superFlow;

        public LazySubFlowElem(int index, LazyFlowElem superFlow) {
            this.index = index;
            this.superFlow = superFlow;
        }


        @Override
        public <K, V> LazySubFlow process(Lazy<K, V> lazy, K k, BiConsumer<V, LazySubFlow> consumer) {
            superFlow.add(lazy, (flowElem) -> {
                LazySubFlow sub = flowElem.sub(index);
                if (stoped()) {
                    return;
                }
                lazy.add(k, v -> {
                    Objects.requireNonNull(consumer);//keep
                    consumer.accept(v, sub);
                });
            });
            return this;
        }

        private boolean stoped() {
            return superFlow.stoped(index);
        }

        @Override
        public void stop() {
            superFlow.stop(index);
        }
    }

    public static interface LazyFlowFn<S, T> {
        void accept(S s, T t, LazySubFlow flow);
    }

    public static class Unique<ITEM, EX_ARG> {
        public final boolean skipNull;
        public final Set<ITEM> set;
        public final BiFunction<ITEM, EX_ARG, ? extends RuntimeException> ex;

        public Unique(
                boolean skipNull, Set<ITEM> set,
                BiFunction<ITEM, EX_ARG, ? extends RuntimeException> ex) {
            this.skipNull = skipNull;
            this.set = set;
            this.ex = ex;
        }

        public Unique(boolean skipNull, BiFunction<ITEM, EX_ARG, ? extends RuntimeException> ex) {
            this(skipNull, new LinkedHashSet<>(), ex);
        }

        public Unique(boolean skipNull, int size,
                      BiFunction<ITEM, EX_ARG, ? extends RuntimeException> ex) {
            this(skipNull, new LinkedHashSet<>(size), ex);
        }

        /**
         * add
         *
         * @param o   unique object
         * @param arg exception arg
         * @return this
         */
        public Unique<ITEM, EX_ARG> add(ITEM o, EX_ARG arg) {
            if (o == null) {
                if (skipNull) {
                    return this;
                }
            } else {
                if (set.add(o)) {
                    return this;
                }
            }
            RuntimeException ex = this.ex.apply(o, arg);
            if (ex == null) {
                return this;
            }
            throw ex;
        }

        public Unique<ITEM, EX_ARG> add(ITEM o) {
            return add(o, null);
        }

        public Set<ITEM> toSet() {
            return set;
        }

        public List<ITEM> toList() {
            return ListUtil.toList(set);
        }
    }

    public static <ITEM, EX_ARG> Unique<ITEM, EX_ARG> unique(
            boolean skipNull, BiFunction<ITEM, EX_ARG, ? extends RuntimeException> ex) {
        return new Unique<>(skipNull, ex);
    }

    public static <ITEM, EX_ARG> Unique<ITEM, EX_ARG> unique(boolean skipNull) {
        return new Unique<>(skipNull, (a, b) -> null);
    }
}
