package com.github.mybsam12138.common.filter;

import com.github.mybsam12138.common.context.RequestContext;
import com.github.mybsam12138.common.context.RequestContextData;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "API_LOG")
public class ApiLogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        long start = System.currentTimeMillis();

        String method = request.getMethod();
        String uri = request.getRequestURI();

        RequestContextData ctx = RequestContext.get();

        try {
            filterChain.doFilter(request, response);

            long cost = System.currentTimeMillis() - start;
            int status = response.getStatus();

            log.info("method={} uri={} status={} cost={}ms traceId={} tenantId={} clientIp={}", method, uri, status,
                    cost, ctx != null ? ctx.getTraceId() : null, ctx != null ? ctx.getTenantId() : null, ctx != null
                            ? ctx.getClientIp() : null);

        } catch (Exception ex) {

            long cost = System.currentTimeMillis() - start;

            log.error("method={} uri={} cost={}ms traceId={} tenantId={} clientIp={}", method, uri, cost,
                    ctx != null ? ctx.getTraceId() : null, ctx != null ? ctx.getTenantId() : null, ctx != null ?
                            ctx.getClientIp() : null, ex);

            throw ex;
        }
    }

    /**
     * Optional: skip health / metrics / static resources
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/health") || path.startsWith("/actuator") || path.startsWith("/metrics");
    }
}