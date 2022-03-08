package com.bingoabin.utils;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.*;

/**
 * @author HanJunsheng
 * @date 2021/7/16
 * @copyright sankuai.com
 */
public class FnUtil {
    private static Consumer LOG = e -> {
    };

    private FnUtil() {
        super();
    }


    public static interface SafeEx<T> {
        Optional<T> check(Callable<T> callable);
    }

    public static <T> SafeEx<T> safeEx(Consumer<Exception> ex) {
        return callable -> safe(callable, ex);
    }

    public static <T> Optional<T> safe(Callable<T> callable) {
        return safe(callable, e -> {
        });
    }

    @SuppressWarnings("all")
    public static <T> T safeToRuntime(Callable<T> callable) {
        return safe(callable, e -> {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            }
            throw new RuntimeException(e);
        }).get();
    }

    public static void safeToRuntime(Task task) {
        safeToRuntime(() -> {
            task.run();
            return task;
        });
    }

    public static <T> Optional<T> safe(Callable<T> callable, Consumer<Exception> onException) {
        try {
            return Optional.ofNullable(callable.call());
        } catch (Exception e) {
            onException.accept(e);
            return Optional.empty();
        }
    }


    public static Optional<Exception> safeTask(Task task) {
        try {
            task.run();
            return Optional.empty();
        } catch (Exception e) {
            return Optional.of(e);
        }
    }

    public static <T, R> Function<T, R> safeFn(ExFn<T, R> fn) {
        return t -> safe(() -> fn.apply(t)).orElse(null);
    }

    public static class Curry<T, P, R> {
        private BiFunction<T, P, R> biFunction;

        public Curry(BiFunction<T, P, R> biFunction) {
            this.biFunction = biFunction;
        }

        public Function<P, R> left(T t) {
            return p -> biFunction.apply(t, p);
        }

        public Function<T, R> right(P p) {
            return t -> biFunction.apply(t, p);
        }
    }

    public static <T, R, P> Curry<T, P, R> curry(BiFunction<T, P, R> biFunction) {
        return new Curry<>(biFunction);
    }

    public static <T, R, P> Fn<T, R> curry(BiFunction<T, P, R> biFunction, P p) {
        return t -> biFunction.apply(t, p);
    }

    public static <T, R, P> Fn<T, R> curryLeft(BiFunction<P, T, R> biFunction, P p) {
        return t -> biFunction.apply(p, t);
    }

    public static <T, R> Fn<T, R> of(Function<T, R> function) {
        return function::apply;
    }

    public static <T, R> Consumer<T> eat(Function<T, R> function) {
        return function::apply;
    }

    public static <T> Fn<T, T> peek(Consumer<T> consumer) {
        return t -> {
            consumer.accept(t);
            return t;
        };
    }

    public static interface Fn<T, R> extends Function<T, R> {
        default Consumer<T> eat(Consumer<R> consumer) {
            return t -> consumer.accept(this.apply(t));
        }

        default Consumer<T> eat() {
            return this::apply;
        }
    }

    public static final Fn<?, ?> NULL_FN = e -> null;

    public static <T, R> Fn<T, R> getNull() {
        return (Fn<T, R>) NULL_FN;
    }

    public static <T, R> Fn<T, R> getNull(Consumer<T> consumer) {
        return t -> {
            consumer.accept(t);
            return null;
        };
    }

    public static <T> Supplier<T> getNull(Runnable fn) {
        return () -> {
            fn.run();
            return null;
        };
    }

    public static <T> Fn<T, T> id(Consumer<T> consumer) {
        return e -> {
            consumer.accept(e);
            return e;
        };
    }

    public static <T> Function<T, T> or(T val) {
        return s -> s == null ? val : s;
    }

    public static <T> Supplier<T> once(Supplier<T> target) {
        AtomicReference<Supplier<T>> delegate = new AtomicReference<>();
        Supplier<T>[] builder = new Supplier[1];
        builder[0] = () -> {
            if (delegate.get() != builder[0]) {
                return delegate.get().get();
            }
            synchronized (builder[0]) {
                if (delegate.get() != builder[0]) {
                    return delegate.get().get();
                }
                T t = target.get();
                delegate.set(() -> t);
                return t;
            }
        };
        delegate.set(builder[0]);
        return () -> delegate.get().get();
    }

    public static interface ExFn<T, R> extends Function<T, R> {
        @SuppressWarnings("all")
        R exec(T t) throws Exception;

        @Override
        default R apply(T t) {
            return safeToRuntime(() -> exec(t));
        }
    }

    public static interface Varg {
        public Object apply(Object... args);
    }

    public static <T, R> BiConsumer<T, R> biCon1(Consumer<T> consumer) {
        return (k, v) -> consumer.accept(k);
    }

    public static <T, R> BiConsumer<T, R> biCon2(Consumer<R> consumer) {
        return (k, v) -> consumer.accept(v);
    }

    public static interface Task {
        void run() throws Exception;
    }


    public static <T> When<T> when(T t) {
        return new When<>(t);
    }

    public static <T> When<T> when(T t, Predicate<T> test) {
        return when(t).and(test);
    }

    public static class When<T> {
        private T o;

        private When(T o) {
            this.o = o;
        }

        public <R> Optional<R> then(Function<T, R> map) {
            return Optional.ofNullable(o).map(map);
        }

        public <R> Optional<R> safe(Function<T, R> map) {
            try {
                return then(map);
            } catch (Exception e) {
                LOG.accept(e);
                return Optional.empty();
            }
        }

        public When<T> and(Predicate<T> test) {
            if (o == null) {
                return this;
            }
            if (!test.test(o)) {
                o = null;
            }
            return this;
        }

        public When<T> andNot(Predicate<T> test) {
            return and(test.negate());
        }


        public void next(Consumer<T> map) {
            if (o != null) {
                map.accept(o);
            }
        }
    }

    public static interface ExConsumer<T> {
        void accept(T t) throws Exception;
    }

    public static <T> int all(ExConsumer<T> exConsumer, T... ts) {
        Objects.requireNonNull(exConsumer);
        if (ts == null || ts.length == 0) {
            return 0;
        }
        int i = 0;
        for (T t : ts) {
            Optional<Exception> e = FnUtil.safeTask(() -> exConsumer.accept(t));
            if (e.isPresent()) {
                i++;
            }
        }
        return i;
    }

    public static class With<ARG, RESULT> {
        public final ARG arg;
        public final RESULT result;

        public With(ARG arg, RESULT result) {
            this.arg = arg;
            this.result = result;
        }

        public ARG getArg() {
            return arg;
        }

        public RESULT getResult() {
            return result;
        }

        @Override
        public String toString() {
            return "With{" +
                    "arg=" + arg +
                    ", result=" + result +
                    '}';
        }
    }

    /**
     * Applies this function to the given argument.
     * And return both the [fn arg] and the [fn result]
     *
     * @param fn
     * @param <ARG>
     * @param <RESULT>
     * @return
     */
    public static <ARG, RESULT>
    Function<ARG, With<ARG, RESULT>> with(Function<ARG, RESULT> fn) {
        return o -> new With<>(o, fn.apply(o));
    }

    /**
     * Applies this function to the given argument.
     * And return both the [fn arg] and the [fn result].
     * If [fn arg] is null, return null.
     *
     * @param fn
     * @param <ARG>
     * @param <RESULT>
     * @return
     */
    public static <ARG, RESULT>
    Function<ARG, With<ARG, RESULT>> withOrNullArg(Function<ARG, RESULT> fn) {
        return o -> o == null ? null : new With<>(o, fn.apply(o));
    }

    /**
     * Applies this function to the given argument.
     * And return both the [fn arg] and the [fn result].
     * If [fn result] is null, return null.
     *
     * @param fn
     * @param <ARG>
     * @param <RESULT>
     * @return
     */
    public static <ARG, RESULT>
    Function<ARG, With<ARG, RESULT>> withOrNullResult(Function<ARG, RESULT> fn) {
        return o -> {
            RESULT result = fn.apply(o);
            return result == null ? null : new With<>(o, result);
        };
    }


    /**
     * Applies this function to the given argument.
     * And return both the [fn arg] and the [fn result].
     * If [fn arg] or [fn result] is null, return null.
     *
     * @param fn
     * @param <ARG>
     * @param <RESULT>
     * @return
     */
    public static <ARG, RESULT>
    Function<ARG, With<ARG, RESULT>> withOrNull(Function<ARG, RESULT> fn) {
        return o -> {
            if (o == null) {
                return null;
            }
            RESULT result = fn.apply(o);
            return result == null ? null : new With<>(o, result);
        };
    }
}
