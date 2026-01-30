package com.github.mybsam12138.common.util;

import java.util.UUID;

public final class TraceIdUtils {

    private TraceIdUtils() {}

    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean isValid(String traceId) {
        return traceId != null && traceId.length() >= 16;
    }
}