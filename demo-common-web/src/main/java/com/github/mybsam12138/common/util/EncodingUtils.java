package com.github.mybsam12138.common.util;

import java.nio.charset.StandardCharsets;

public final class EncodingUtils {

    private EncodingUtils() {}

    public static byte[] toUtf8Bytes(String text) {
        return text == null ? null : text.getBytes(StandardCharsets.UTF_8);
    }

    public static String fromUtf8Bytes(byte[] bytes) {
        return bytes == null ? null : new String(bytes, StandardCharsets.UTF_8);
    }
}