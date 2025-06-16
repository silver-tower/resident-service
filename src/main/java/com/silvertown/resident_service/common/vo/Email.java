package com.silvertown.resident_service.common.vo;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;

public record Email(String value) {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public Email {
        if (value == null || value.isBlank()) {
            throw CustomError.of(ErrorCode.BAD_REQUEST);
        }
        if (!isValidEmail(value)) {
            throw CustomError.of(ErrorCode.BAD_REQUEST);
        }
    }

    private static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }
}
