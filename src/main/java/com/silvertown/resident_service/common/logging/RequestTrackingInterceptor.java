package com.silvertown.resident_service.common.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class RequestTrackingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request instanceof CachedBodyHttpServletRequest cachedRequest) {
            String requestBody = new String(cachedRequest.getCachedBody(), StandardCharsets.UTF_8).replace("\n", "");
            log.info("[{}] {} {} QueryParams={}, Body={}",
                    getClientIp(request),
                    request.getMethod(),
                    request.getRequestURI(),
                    request.getQueryString(),
                    requestBody
            );
        } else {
            log.warn("Request body not cached. Skipping logging.");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        boolean isExcludeLogResponse = response.getContentType().contains("text/html") || response.getContentType().contains("application/octet-stream");
        if (isExcludeLogResponse) {
            log.info("[{}] Status={}",
                    getClientIp(request),
                    response.getStatus()
            );
            return;
        } else if(response instanceof ContentCachingResponseWrapper cachingResponse){
            String responseBody = extractResponseBody(cachingResponse);
            log.info("[{}] Status={} responseType={} responseBody={}",
                    getClientIp(request),
                    response.getStatus(),
                    cachingResponse.getContentType(),
                    responseBody
            );
        }
    }

    private String extractResponseBody(ContentCachingResponseWrapper response) {
        try {
            byte[] content = response.getContentAsByteArray();
            return content.length > 0
                    ? new String(content, StandardCharsets.UTF_8)
                    : "{}";
        } catch (Exception e) {
            log.error("Failed to extract response body", e);
            return "{}";
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        return ip != null && !ip.isEmpty() ? ip : request.getRemoteAddr();
    }
}
