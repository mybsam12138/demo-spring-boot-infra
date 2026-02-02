package com.github.mybsam12138.common.filter;

import com.github.mybsam12138.common.context.RequestContext;
import com.github.mybsam12138.common.context.RequestContextData;
import com.github.mybsam12138.common.util.staticutil.RequestUtils;
import com.github.mybsam12138.common.util.staticutil.TraceIdUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
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
                    .traceId(TraceIdUtils.generate())
                    .clientIp(RequestUtils.getClientIp(request))
                    .tenantId(tenantId)
                    .build();
            RequestContext.set(context);
            MDC.put("traceId",context.getTraceId());
            filterChain.doFilter(request, response);
        } catch (Throwable ex){
            log.error("Request Context Filter error. ",ex);
            throw ex;
        }finally {
            MDC.clear();
            RequestContext.clear(); // MUST be in finally
        }
    }


}