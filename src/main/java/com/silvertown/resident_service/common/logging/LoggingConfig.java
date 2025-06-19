package com.silvertown.resident_service.common.logging;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoggingConfig implements WebMvcConfigurer {

    private final RequestTrackingInterceptor requestUUIDInterceptor;

    public LoggingConfig(RequestTrackingInterceptor requestUUIDInterceptor) {
        this.requestUUIDInterceptor = requestUUIDInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestUUIDInterceptor)
                .addPathPatterns("/**");
    }
}
