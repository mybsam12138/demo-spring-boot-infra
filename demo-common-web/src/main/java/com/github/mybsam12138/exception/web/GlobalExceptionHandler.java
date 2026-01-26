package com.github.mybsam12138.exception.web;

import com.github.mybsam12138.exception.base.BaseException;
import com.github.mybsam12138.exception.support.DefaultErrorCodes;
import com.github.mybsam12138.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<Void>> handleBaseException(BaseException ex) {
        HttpStatus status = ExceptionTranslator.translate(ex);
        return ResponseEntity
                .status(status)
                .body(ApiResponse.error(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnknown(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(
                        DefaultErrorCodes.SYSTEM_ERROR,
                        "Unexpected system error"
                ));
    }
}