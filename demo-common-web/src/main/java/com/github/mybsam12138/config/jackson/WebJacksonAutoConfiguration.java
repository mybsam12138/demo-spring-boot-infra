package com.github.mybsam12138.config.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.mybsam12138.converter.deserializer.LocalDateTimeDeserializer;
import com.github.mybsam12138.converter.deserializer.TrimStringDeserializer;
import com.github.mybsam12138.converter.serializer.LocalDateTimeSerializer;
import com.github.mybsam12138.converter.serializer.LongToStringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.LocalDateTime;

@Configuration
@ConditionalOnClass(ObjectMapper.class)
public class WebJacksonAutoConfiguration {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = JsonMapper.builder()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .build();

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());

        SimpleModule commonModule = new SimpleModule();
        commonModule.addSerializer(Long.class, new LongToStringSerializer());
        commonModule.addDeserializer(String.class, new TrimStringDeserializer());

        mapper.registerModule(javaTimeModule);
        mapper.registerModule(commonModule);


        return mapper;
    }

    @Bean
    public HttpMessageConverters httpMessageConverters(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter jacksonConverter =
                new MappingJackson2HttpMessageConverter(objectMapper);
        return new HttpMessageConverters(jacksonConverter);
    }
}