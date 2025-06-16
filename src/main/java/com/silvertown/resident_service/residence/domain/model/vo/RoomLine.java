package com.silvertown.resident_service.residence.domain.model.vo;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;

public record RoomLine(String line) {
    static int MAX_LENGTH = 10;

    public RoomLine {
        if(line == null) throw CustomError.of(ErrorCode.RESIDENCE_BAD_REQUEST);
        if(line.isBlank()) throw CustomError.of(ErrorCode.RESIDENCE_BAD_REQUEST);
        if(line.length() > MAX_LENGTH) throw CustomError.of(ErrorCode.RESIDENCE_BAD_REQUEST);
    }
}
