package com.silvertown.resident_service.person.domain.model.vo;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;

import java.util.Calendar;
import java.util.Date;

public record BirthDate(Date date) {
    public BirthDate {
        if (date == null) throw CustomError.of(ErrorCode.USER_INPUT_INVALID);

        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        Date nowDate = now.getTime();

        if (!date.before(nowDate)) throw CustomError.of(ErrorCode.USER_INPUT_INVALID);
    }

}
