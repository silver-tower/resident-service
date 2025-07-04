package com.silvertown.resident_service.person.domain.model.vo;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public record BirthDate(LocalDate date) {
    public BirthDate {
        if (date == null) throw CustomError.of(ErrorCode.USER_INVALID_DATA);
        LocalDate nowDate = LocalDate.now();
        boolean isBeforeNow = date.isBefore(nowDate);
        if (!isBeforeNow) throw CustomError.of(ErrorCode.USER_BAD_REQUEST);
    }

}
