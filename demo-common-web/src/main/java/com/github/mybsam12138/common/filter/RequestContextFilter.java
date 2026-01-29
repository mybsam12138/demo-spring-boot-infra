package com.github.mybsam12138.common.filter;

import com.github.mybsam12138.common.context.RequestContext;
import com.github.mybsam12138.common.context.RequestContextData;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RequestContextFilter extends OncePerRequestFilter {

    private static final String HEADER_TENANT_ID = "X-Tenant-Id";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String tenantId = request.getHeader(HEADER_TENANT_ID);

        try {
            RequestContextData context = RequestContextData.builder()
                    .traceId(generateTraceId())
                    .clientIp(resolveClientIp(request))
                    .tenantId(tenantId)
                    .build();
            RequestContext.set(context);
            MDC.put("traceId",context.getTraceId());
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
            RequestContext.clear(); // MUST be in finally
        }
    }

    private String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    private String resolveClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isBlank()) {
            return forwarded.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}