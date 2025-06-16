package com.silvertown.resident_service.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonIgnoreProperties({
        "cause",
        "stackTrace",
        "suppressed",
        "localizedMessage",
        "httpStatus",
})
public class CustomError extends RuntimeException {
    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    private CustomError(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
    public static CustomError of(ErrorCode errorCode) {
        return new CustomError(
            errorCode.getCode(), 
            errorCode.getMessage(),
            errorCode.getHttpStatus()
        );
    }
}
