package com.silvertown.resident_service.common.vo;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;

public record Name(String name) {
    static int MIN_LENGTH = 2;
    static int MAX_LENGTH = 100;
    public Name {
        if (name.isEmpty()) throw CustomError.of(ErrorCode.BAD_REQUEST);
        if (name.length() < MIN_LENGTH) throw CustomError.of(ErrorCode.BAD_REQUEST);
        if (name.length() > MAX_LENGTH) throw CustomError.of(ErrorCode.BAD_REQUEST);
    }
}
