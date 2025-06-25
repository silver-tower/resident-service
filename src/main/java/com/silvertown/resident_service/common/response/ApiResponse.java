package com.silvertown.resident_service.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ApiResponse<T> {
    private final boolean success;
    private final LocalDateTime timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final CustomError error;
    private final UUID uuid;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .error(null)
                .uuid(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .success(false)
                .error(CustomError.of(errorCode))
                .uuid(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> error(CustomError error) {
        return ApiResponse.<T>builder()
                .success(false)
                .error(error)
                .uuid(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .build();
    }
}