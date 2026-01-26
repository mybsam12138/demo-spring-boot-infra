package com.github.mybsam12138.exception.system;

import com.github.mybsam12138.exception.base.BaseException;
import com.github.mybsam12138.exception.base.ErrorCode;

public class ValidationException extends BaseException {

    public ValidationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}