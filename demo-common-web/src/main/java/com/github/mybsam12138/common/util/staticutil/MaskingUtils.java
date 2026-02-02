package com.github.mybsam12138.common.util.staticutil;

public final class MaskingUtils {

    private MaskingUtils() {}

    public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) return email;
        int idx = email.indexOf('@');
        return email.charAt(0) + "****" + email.substring(idx);
    }

    public static String maskPhone(String phone) {
        if (phone == null || phone.length() < 7) return phone;
        return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
    }
}