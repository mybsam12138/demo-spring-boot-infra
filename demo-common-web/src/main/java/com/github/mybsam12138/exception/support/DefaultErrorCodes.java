package com.github.mybsam12138.exception.support;

import com.github.mybsam12138.exception.base.ErrorCode;

public enum DefaultErrorCodes implements ErrorCode {

    /* =========================
     * 0–999 : System / Unknown
     * ========================= */
    SYSTEM_ERROR(500, "System error"),
    UNKNOWN_ERROR(501, "Unknown error"),

    /* =========================
     * 1000–1999 : Web / Request / Validation
     * ========================= */
    INVALID_REQUEST(1000, "Invalid request"),
    INVALID_PARAMETER(1001, "Invalid request parameter"),
    MISSING_PARAMETER(1002, "Missing required parameter"),
    PARAMETER_FORMAT_ERROR(1003, "Parameter format error"),
    REQUEST_NOT_READABLE(1004, "Request body is not readable"),

    /* =========================
     * 2000–2999 : Auth / Security
     * ========================= */
    UNAUTHORIZED(2000, "Unauthorized"),
    FORBIDDEN(2001, "Forbidden"),
    TOKEN_EXPIRED(2002, "Token expired"),
    TOKEN_INVALID(2003, "Invalid token"),

    /* =========================
     * 3000–3999 : Infrastructure
     * ========================= */
    DATABASE_ERROR(3000, "Database error"),
    REDIS_ERROR(3001, "Redis error"),
    RPC_ERROR(3002, "Remote service call failed"),
    IO_ERROR(3003, "IO error"),
    TIMEOUT(3004, "Operation timeout");

    private final int code;
    private final String message;

    DefaultErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}