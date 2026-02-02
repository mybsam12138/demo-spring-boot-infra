package com.github.mybsam12138.common.util.staticutil;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtils {

    private DateTimeUtils() {}

    public static Instant nowUtc() {
        return Instant.now();
    }

    public static String formatIso(Instant instant) {
        return instant == null ? null : DateTimeFormatter.ISO_INSTANT.format(instant);
    }

    public static Instant parseIso(String text) {
        return text == null ? null : Instant.parse(text);
    }
}