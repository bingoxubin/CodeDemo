package com.bingoabin.utils;

/**
 * @Author: xubin34
 * @Date: 2022/2/17 10:21 上午
 * @CopyRight: sankuai.com
 * @Description:
 */

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class LazyInit<T> {
    private volatile T data;
    private volatile boolean inited;
    private final Supplier<T> gen;

    public static <T> LazyInit<T> of(Supplier<T> gen) {
        return new LazyInit(gen);
    }

    public static <T, C> LazyInit<T> of(C c, Function<C, T> gen) {
        return new LazyInit(() -> gen.apply(c));
    }

    public static <T> LazyInit<T> of(T data) {
        return new LazyInit(() -> data);
    }

    public LazyInit(Supplier<T> gen) {
        this.gen = gen;
    }

    public T current() {
        return data;
    }

    public T get() {
        if (inited) {
            return data;
        }
        synchronized (this) {
            if (inited) {
                return data;
            }
            inited = true;
            data = gen.get();
            return data;
        }
    }

    public Optional<T> tryGet() {
        return Optional.ofNullable(get());
    }
}

