package com.github.mybsam12138.exception.base;

public interface ErrorCode {

    /** Stable error code, e.g. USER_001 */
    int code();

    /** Default human-readable message */
    String message();
}