package com.rabobank.statementprocessor.util;

import java.util.function.Supplier;

@FunctionalInterface
public interface ThwringSupplier<T, E extends Throwable> {

    public static <T, E extends Throwable> Supplier<T> unchecked(ThwringSupplier<T, E> s) {
        return () -> {
            try {
                return s.get();
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

    T get() throws E;
}
