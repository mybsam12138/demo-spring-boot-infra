package com.github.mybsam12138.common.exception.web;


import com.github.mybsam12138.common.exception.base.ErrorCode;
import com.github.mybsam12138.common.exception.support.DefaultErrorCodes;
import com.github.mybsam12138.common.response.ApiResponse;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Read-only API to expose all registered error codes.
 *
 * <p>
 * This endpoint is intended for:
 * <ul>
 *   <li>Frontend error-code mapping</li>
 *   <li>Internal admin / debug tools</li>
 *   <li>Documentation generation</li>
 * </ul>
 *
 * Disabled by default.
 */
@RestController
@RequestMapping("/internal/error-codes")
public class ErrorCodeQueryController {

    @GetMapping
    public ApiResponse<List<ErrorCodeView>> listAll() {
        List<ErrorCodeView> result = Arrays.stream(DefaultErrorCodes.values())
                .sorted(Comparator.comparing(ErrorCode::code))
                .map(ErrorCodeView::from)
                .collect(Collectors.toList());

        return ApiResponse.success(result);
    }
}
