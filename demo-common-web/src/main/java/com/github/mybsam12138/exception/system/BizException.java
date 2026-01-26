package com.github.mybsam12138.exception.system;

import com.github.mybsam12138.exception.base.BaseException;
import com.github.mybsam12138.exception.base.ErrorCode;

public class BizException extends BaseException {

    public BizException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BizException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}