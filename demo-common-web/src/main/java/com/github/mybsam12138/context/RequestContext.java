package com.github.mybsam12138.context;

public final class RequestContext {
    private static final ThreadLocal<RequestContextData> HOLDER = new ThreadLocal<>();

    public static void set(RequestContextData data) {
        HOLDER.set(data);
    }

    public static RequestContextData get() {
        return HOLDER.get();
    }

    public static void clear() {
        HOLDER.remove();
    }
}