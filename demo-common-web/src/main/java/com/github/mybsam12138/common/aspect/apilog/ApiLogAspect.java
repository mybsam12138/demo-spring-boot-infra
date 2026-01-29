package com.github.mybsam12138.common.aspect.apilog;

import com.github.mybsam12138.common.context.RequestContext;
import com.github.mybsam12138.common.context.RequestContextData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Order(0) // early, but after filters
@Component
public class ApiLogAspect {

    @Around("@within(controller)")
    public Object logApi(ProceedingJoinPoint pjp, Controller controller) throws Throwable {

        long start = System.currentTimeMillis();

        HttpServletRequest request = currentRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        RequestContextData ctx = RequestContext.get();

        try {
            Object result = pjp.proceed();

            long cost = System.currentTimeMillis() - start;

            log.info("[API] {} {} success, cost={}ms, traceId={}, tenantId={}, clientIp={}", method, uri, cost,
                    ctx != null ? ctx.getTraceId() : null, ctx != null ? ctx.getTenantId() : null, ctx != null ?
                            ctx.getClientIp() : null);

            return result;

        } catch (Throwable ex) {

            long cost = System.currentTimeMillis() - start;

            log.error("[API] {} {} failed, cost={}ms, traceId={}, tenantId={}, clientIp={}", method, uri, cost,
                    ctx != null ? ctx.getTraceId() : null, ctx != null ? ctx.getTenantId() : null, ctx != null ?
                            ctx.getClientIp() : null, ex);

            throw ex;
        }
    }

    private HttpServletRequest currentRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }
}