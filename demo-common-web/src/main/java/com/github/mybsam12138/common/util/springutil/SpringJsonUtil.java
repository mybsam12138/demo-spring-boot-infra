package com.github.mybsam12138.common.util.springutil;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public final class SpringJsonUtil {

    private static ObjectMapper mapper;

    public SpringJsonUtil(ObjectMapper mapper) {
        SpringJsonUtil.mapper = mapper;
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new IllegalStateException("JSON serialization failed", e);
        }
    }

    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (Exception e) {
            throw new IllegalStateException("JSON deserialization failed", e);
        }
    }
}