package com.silvertown.resident_service.person.application.query;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;

public record GetPersonQuery(int personId) {
    public GetPersonQuery {
        if (personId<1) throw CustomError.of(ErrorCode.USER_BAD_REQUEST);
    }
}
