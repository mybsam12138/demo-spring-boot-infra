package com.github.mybsam12138.exception.base;

public abstract class BaseException extends RuntimeException {

    private final ErrorCode errorCode;
    private Object[] args =null;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.defaultMessage());
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, Object[] args) {
        super(errorCode.defaultMessage());
        this.errorCode = errorCode;
        this.args = args;
    }

    public BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs(){
        return args;
    }
}