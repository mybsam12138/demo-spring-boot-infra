package com.github.mybsam12138.context;

import lombok.Data;

@Data
public class RequestContextData {
    private String traceId;
    private String tenantId;
    private String clientIp;
}