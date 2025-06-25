package com.silvertown.resident_service.person.domain.model.vo;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;

public record LoginId(String loginId) {
    static int MAX_LENGTH = 10;
    static int MIN_LENGTH = 5;

    public LoginId {
        if (loginId.isEmpty()) throw CustomError.of(ErrorCode.USER_INVALID_DATA);
        if (loginId.contains(" ")) throw CustomError.of(ErrorCode.USER_BAD_REQUEST);
        if (loginId.length() > LoginId.MAX_LENGTH) throw CustomError.of(ErrorCode.USER_BAD_REQUEST);
        if (loginId.length() < LoginId.MIN_LENGTH) throw CustomError.of(ErrorCode.USER_BAD_REQUEST);
        if (!loginId.matches("^[a-zA-Z0-9]*$")) throw CustomError.of(ErrorCode.USER_BAD_REQUEST);
    }
}



