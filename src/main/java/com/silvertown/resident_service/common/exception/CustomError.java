package com.silvertown.resident_service.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(force = true)
@JsonIgnoreProperties({
        "cause",
        "stackTrace",
        "suppressed",
        "localizedMessage",
        "httpStatus",
        "error",
})
public class CustomError extends RuntimeException {
    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
    private final Exception error;

    private CustomError(String code, String message, HttpStatus httpStatus, Exception error) {
        super(message);
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
        this.error = error;
    }

    private CustomError(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
        this.error = null;
    }

    public static CustomError of(ErrorCode errorCode, Exception e) {
        return new CustomError(
                errorCode.getCode(),
                errorCode.getMessage(),
                errorCode.getHttpStatus(),
                e
        );
    }

    public static CustomError of(ErrorCode errorCode) {
        return new CustomError(
                errorCode.getCode(),
                errorCode.getMessage(),
                errorCode.getHttpStatus()
        );
    }

}
