package com.rabobank.statementprocessor.util;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Throwable> {

    public static <T, R, E extends Throwable> Function<T, R> unchecked(ThrowingFunction<T, R, E> f) {

        return t -> {
            try {
                return f.apply(t);
            } catch (Throwable e) {
                throwCheckedUnchecked(e);
            }
            return null;
        };
    }

    @SuppressWarnings("unchecked")
    static <X extends Throwable> void throwCheckedUnchecked(Throwable t) throws X {
        throw (X) t;
    }

    R apply(T t) throws E;
}