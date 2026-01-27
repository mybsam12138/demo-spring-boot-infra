package com.github.mybsam12138.filter;

import com.github.mybsam12138.context.RequestContext;
import com.github.mybsam12138.context.RequestContextData;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RequestContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            RequestContextData context = RequestContextData.builder()
                    .traceId(generateTraceId())
                    .clientIp(resolveClientIp(request))
                    .build();
            RequestContext.set(context);
            filterChain.doFilter(request, response);
        } finally {
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