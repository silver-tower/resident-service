package com.silvertown.resident_service.residence.domain.model.vo;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;

public record Floor(String floor) {
    static int MAX_LENGTH = 10;

    public Floor {
        if (floor == null) throw CustomError.of(ErrorCode.RESIDENCE_BAD_REQUEST);
        if (floor.isBlank()) throw CustomError.of(ErrorCode.RESIDENCE_BAD_REQUEST);
        if (floor.length() > MAX_LENGTH) throw CustomError.of(ErrorCode.RESIDENCE_BAD_REQUEST);
    }
}
