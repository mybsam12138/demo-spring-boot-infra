package com.github.mybsam12138.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class WebI18nAutoConfiguration {

    @Bean
    public MessageSource webCommonMessageSource() {
        ReloadableResourceBundleMessageSource ms =
                new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:i18n");
        ms.setDefaultEncoding("UTF-8");
        ms.setFallbackToSystemLocale(false);
        return ms;
    }

    /**
     * Bridge MessageSource that allows application messages
     * to override demo-common-web messages explicitly.
     */
    @Bean
    public MessageSource messageSource(MessageSource webCommonMessageSource) {
        DelegatingMessageSource delegating = new DelegatingMessageSource();
        delegating.setParentMessageSource(webCommonMessageSource);
        return delegating;
    }
}