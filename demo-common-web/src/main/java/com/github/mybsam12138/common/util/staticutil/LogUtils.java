package com.github.mybsam12138.common.util.staticutil;

public final class LogUtils {

    private static final int DEFAULT_MAX_LEN = 2000;

    private LogUtils() {}

    public static String safeToString(Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            return abbreviate(obj.toString(), DEFAULT_MAX_LEN);
        } catch (Exception ex) {
            return "[toString() failed: " + ex.getClass().getSimpleName() + "]";
        }
    }

    public static String abbreviate(String text, int maxLen) {
        if (text == null || text.length() <= maxLen) {
            return text;
        }
        return text.substring(0, maxLen) + "...(truncated)";
    }
}