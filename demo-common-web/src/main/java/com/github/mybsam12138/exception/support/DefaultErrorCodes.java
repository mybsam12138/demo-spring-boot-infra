package com.github.mybsam12138.exception.support;

import com.github.mybsam12138.exception.base.ErrorCode;

public enum DefaultErrorCodes implements ErrorCode {

    /* =========================
     * 0–999 : System / Unknown
     * ========================= */
    SYSTEM_ERROR(500, "error.system", "System error"),

    UNKNOWN_ERROR(501, "error.unknown", "Unknown error"),


    /* =========================
     * 1000–1999 : Web / Request / Validation
     * ========================= */
    INVALID_REQUEST(1000, "error.request.invalid", "Invalid request"),

    INVALID_PARAMETER(1001, "error.request.invalid_parameter", "Invalid requeest parameter"),

    MISSING_PARAMETER(1002, "error.request.missing_parameter", "Missing required parameter"),

    PARAMETER_FORMAT_ERROR(1003, "error.request.parameter_format", "Parameter format error"),

    REQUEST_NOT_READABLE(1004, "error.request.not_readable", "Request body is not readable"),


    /* =========================
     * 2000–2999 : Auth / Security
     * ========================= */
    UNAUTHORIZED(2000, "error.auth.unauthorized", "Unauthorized"),

    FORBIDDEN(2001, "error.auth.forbidden", "Forbidden"),

    TOKEN_EXPIRED(2002, "error.auth.token_expired", "Token expired"),

    TOKEN_INVALID(2003, "error.auth.token_invalid", "Invalid token"),

    MISS_USER_ID(2004, "error.auth.missing_user_id", "Missing user id"),


    /* =========================
     * 3000–3999 : Infrastructure
     * ========================= */
    DATABASE_ERROR(3000, "error.infra.database", "Database error"),

    REDIS_ERROR(3001, "error.infra.redis", "Redis error"),

    RPC_ERROR(3002, "error.infra.rpc", "Remote service call failed"),

    IO_ERROR(3003, "error.infra.io", "IO error"),

    TIMEOUT(3004, "error.infra.timeout", "Operation timeout"),

    REQUEST_CONTEXT_NOT_INITIALIZED(3005, "error.infra.request_context_not_initialized", "Request context not " +
            "initialized");

    private final int code;
    private final String i18nKey;
    private final String defaultMessage;

    DefaultErrorCodes(int code, String i18nKey, String defaultMessage) {
        this.code = code;
        this.i18nKey = i18nKey;
        this.defaultMessage = defaultMessage;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String i18nKey() {
        return i18nKey;
    }

    @Override
    public String defaultMessage() {
        return defaultMessage;
    }
}
