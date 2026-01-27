package com.github.mybsam12138.exception.web;


import com.github.mybsam12138.exception.base.ErrorCode;

/**
 * External view of ErrorCode for frontend / admin usage.
 *
 * This class intentionally hides internal implementation details.
 */
public class ErrorCodeView {

    private int code;
    private String message;

    public static ErrorCodeView from(ErrorCode errorCode) {
        ErrorCodeView view = new ErrorCodeView();
        view.code = errorCode.code();
        view.message = errorCode.message();
        return view;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}