package com.github.mybsam12138.interceptor;

import com.github.mybsam12138.context.RequestContext;
import com.github.mybsam12138.context.RequestContextData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class WebContextInterceptor implements HandlerInterceptor {

    private static final String HEADER_TENANT_ID = "X-Tenant-Id";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {

        RequestContextData ctx = RequestContext.get();
        if (ctx == null) {
            // defensive: should NEVER happen if filter is correct
            throw new IllegalStateException("RequestContext not initialized");
        }
        String tenantId = request.getHeader(HEADER_TENANT_ID);

        // enrich context (builder-style copy)
        RequestContext.set(
                ctx.toBuilder()
                        .tenantId(tenantId)
                        .build()
        );

        return true;
    }
}