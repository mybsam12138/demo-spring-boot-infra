package com.github.mybsam12138.common.util.staticutil;

import java.util.Objects;

public final class ObjectUtils {

    private ObjectUtils() {}

    public static <T> T defaultIfNull(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static boolean equalsSafe(Object a, Object b) {
        return Objects.equals(a, b);
    }
}