package com.silvertown.resident_service.common.exception;

import com.silvertown.resident_service.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomError.class)
    public ResponseEntity<?> handleCustomError(CustomError ex) {
        log.error(ex.toString());
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(ApiResponse.error(ex));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnexpectedException(Exception ex) {
        log.error(ex.toString(),ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
