package com.github.mybsam12138.exception.system;

import com.github.mybsam12138.exception.base.BaseException;
import com.github.mybsam12138.exception.base.ErrorCode;

public class AuthException extends BaseException {

    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}