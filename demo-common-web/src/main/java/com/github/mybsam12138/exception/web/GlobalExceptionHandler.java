package com.github.mybsam12138.exception.web;

import com.github.mybsam12138.exception.base.BaseException;
import com.github.mybsam12138.exception.support.DefaultErrorCodes;
import com.github.mybsam12138.response.ApiResponse;
import com.github.mybsam12138.web.i18n.ErrorMessageResolver;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorMessageResolver errorMessageResolver;

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<Void>> handleBaseException(BaseException ex) {
        HttpStatus status = ExceptionTranslator.translate(ex);
        String message = errorMessageResolver.resolve(ex.getErrorCode(),ex.getArgs());

        return ResponseEntity
                .status(status)
                .body(ApiResponse.error(ex.getErrorCode(), message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnknown(Exception ex) {
        String message = errorMessageResolver.resolve(DefaultErrorCodes.SYSTEM_ERROR);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(
                        DefaultErrorCodes.SYSTEM_ERROR,
                        message
                ));
    }
}