package com.silvertown.resident_service.common.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestTrackingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest cachedRequest = new CachedBodyHttpServletRequest((HttpServletRequest) request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        String requestId = UUID.randomUUID().toString();
        MDC.put(UUIDKey.REQUEST.getKey(), requestId);
        cachedRequest.setAttribute(UUIDKey.REQUEST.getKey(), requestId);
        try {
            chain.doFilter(cachedRequest, wrappedResponse);
            wrappedResponse.copyBodyToResponse();
        } finally {
            MDC.remove(UUIDKey.REQUEST.getKey());
            MDC.clear();
        }
    }
}
