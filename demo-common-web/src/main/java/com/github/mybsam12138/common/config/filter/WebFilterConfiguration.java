package com.github.mybsam12138.common.config.filter;

import com.github.mybsam12138.common.filter.ApiLogFilter;
import com.github.mybsam12138.common.filter.RequestContextFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebFilterConfiguration {

    @Bean
    public FilterRegistrationBean<RequestContextFilter> requestContextFilter() {
        FilterRegistrationBean<RequestContextFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new RequestContextFilter());
        bean.setOrder(0);   // FIRST
        return bean;
    }

    @Bean
    public FilterRegistrationBean<ApiLogFilter> apiLogFilter() {
        FilterRegistrationBean<ApiLogFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new ApiLogFilter());
        bean.setOrder(1);   // AFTER context
        return bean;
    }
}