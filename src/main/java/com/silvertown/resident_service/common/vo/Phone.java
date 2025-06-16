package com.silvertown.resident_service.common.vo;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;

public record Phone(String phone) {
    private static final String PHONE_REGEX = "^01(?:0|1|[6-9])(?:\\d{8}|\\d{7}$)";
    static int MAX_LENGTH = 11;
    static int MIN_LENGTH = 10;

    public Phone {
        if (phone == null || phone.isBlank()) throw CustomError.of(ErrorCode.BAD_REQUEST);
        if (phone.length() > MAX_LENGTH) throw CustomError.of(ErrorCode.BAD_REQUEST);
        if (phone.length() < MIN_LENGTH) throw CustomError.of(ErrorCode.BAD_REQUEST);
        if (!phone.matches(PHONE_REGEX)) throw CustomError.of(ErrorCode.BAD_REQUEST);
    }
}
