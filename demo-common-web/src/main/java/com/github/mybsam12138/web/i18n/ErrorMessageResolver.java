package com.github.mybsam12138.web.i18n;

import com.github.mybsam12138.exception.base.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ErrorMessageResolver {

    private final MessageSource messageSource;

    public String resolve(ErrorCode errorCode, Object... args) {
        try {
            return messageSource.getMessage(
                    errorCode.i18nKey(),
                    args,
                    LocaleContextHolder.getLocale()
            );
        } catch (Exception ex) {
            // absolute fallback
            return errorCode.defaultMessage();
        }
    }
}