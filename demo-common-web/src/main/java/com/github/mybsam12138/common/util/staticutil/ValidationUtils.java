package com.github.mybsam12138.common.util.staticutil;

import java.util.regex.Pattern;

public final class ValidationUtils {

    private static final Pattern EMAIL =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private ValidationUtils() {}

    public static boolean isEmail(String value) {
        return value != null && EMAIL.matcher(value).matches();
    }

    public static boolean isNumeric(String value) {
        if (value == null) return false;
        return value.chars().allMatch(Character::isDigit);
    }
}