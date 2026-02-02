package com.github.mybsam12138.common.util.staticutil;

public final class ExceptionUtils {

    private ExceptionUtils() {}

    public static Throwable getRootCause(Throwable t) {
        Throwable cause = t;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause;
    }

    public static boolean isCausedBy(Throwable t, Class<? extends Throwable> type) {
        Throwable cause = t;
        while (cause != null) {
            if (type.isInstance(cause)) {
                return true;
            }
            cause = cause.getCause();
        }
        return false;
    }

    public static String getStackTrace(Throwable t) {
        StringBuilder sb = new StringBuilder();
        sb.append(t).append("\n");
        for (StackTraceElement e : t.getStackTrace()) {
            sb.append("\tat ").append(e).append("\n");
        }
        return sb.toString();
    }
}