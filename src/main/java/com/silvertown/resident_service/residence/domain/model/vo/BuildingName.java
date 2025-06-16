package com.silvertown.resident_service.residence.domain.model.vo;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;

public record BuildingName(String name) {
    static int MAX_LENGTH = 10;

    public BuildingName {
        if (name.isEmpty()) throw CustomError.of(ErrorCode.RESIDENCE_BAD_REQUEST);
        if (name.isBlank()) throw CustomError.of(ErrorCode.RESIDENCE_BAD_REQUEST);
        if (name.length() > MAX_LENGTH) throw CustomError.of(ErrorCode.RESIDENCE_BAD_REQUEST);
    }
}
