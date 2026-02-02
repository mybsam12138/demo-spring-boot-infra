package com.github.mybsam12138.common.util;

import com.github.mybsam12138.common.exception.base.ErrorCode;
import com.github.mybsam12138.common.exception.system.BizException;

public final class AssertUtils {

    private AssertUtils() {}

    public static void notNull(Object obj, ErrorCode code) {
        if (obj == null) {
            throw new BizException(code);
        }
    }

    public static void notBlank(String text, ErrorCode code) {
        if (text == null || text.isBlank()) {
            throw new BizException(code);
        }
    }

    public static void state(boolean expression, ErrorCode code) {
        if (!expression) {
            throw new BizException(code);
        }
    }
}