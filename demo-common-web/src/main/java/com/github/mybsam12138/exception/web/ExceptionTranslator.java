package com.github.mybsam12138.exception.web;

import com.github.mybsam12138.exception.base.BaseException;
import com.github.mybsam12138.exception.system.AuthException;
import com.github.mybsam12138.exception.system.BizException;
import com.github.mybsam12138.exception.system.InfraException;
import com.github.mybsam12138.exception.system.ValidationException;
import org.springframework.http.HttpStatus;

public final class ExceptionTranslator {

    private ExceptionTranslator() {}

    public static HttpStatus translate(BaseException ex) {
        if (ex instanceof ValidationException) {
            return HttpStatus.BAD_REQUEST;
        }
        if (ex instanceof AuthException) {
            return HttpStatus.UNAUTHORIZED;
        }
        if (ex instanceof BizException) {
            return HttpStatus.OK; // or 400, depending on your policy
        }
        if (ex instanceof InfraException) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}