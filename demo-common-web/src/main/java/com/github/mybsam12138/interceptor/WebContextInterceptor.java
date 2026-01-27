package com.github.mybsam12138.interceptor;

import com.github.mybsam12138.context.RequestContext;
import com.github.mybsam12138.context.RequestContextData;
import com.github.mybsam12138.exception.support.DefaultErrorCodes;
import com.github.mybsam12138.web.i18n.ErrorMessageResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
public class WebContextInterceptor implements HandlerInterceptor {

    private static final String HEADER_TENANT_ID = "X-Tenant-Id";

    private final ErrorMessageResolver errorMessageResolver;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        RequestContextData ctx = RequestContext.get();
        if (ctx == null) {
            // defensive: should NEVER happen if filter is correct
            throw new IllegalStateException(errorMessageResolver.resolve(DefaultErrorCodes.REQUEST_CONTEXT_NOT_INITIALIZED));
        }
        String tenantId = request.getHeader(HEADER_TENANT_ID);

        // enrich context (builder-style copy)
        RequestContext.set(ctx.toBuilder().tenantId(tenantId).build());

        return true;
    }
}