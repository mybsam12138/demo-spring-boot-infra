package com.github.mybsam12138.common.util.staticutil;

import java.util.Collection;

public final class CollectionUtils {

    private CollectionUtils() {}

    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> c) {
        return !isEmpty(c);
    }
}