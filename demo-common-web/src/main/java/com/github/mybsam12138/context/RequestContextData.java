package com.github.mybsam12138.context;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RequestContextData {
    private String traceId;
    private String tenantId;
    private String clientIp;
}