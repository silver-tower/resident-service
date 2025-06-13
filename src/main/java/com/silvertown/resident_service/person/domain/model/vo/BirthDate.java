package com.silvertown.resident_service.person.domain.model.vo;

import java.util.Calendar;
import java.util.Date;

public record BirthDate(Date date) {
    public BirthDate {
        if (date == null) throw new IllegalArgumentException("생년월일을 입력해주세요.");

        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        Date nowDate = now.getTime();

        if (!date.before(nowDate)) throw new IllegalArgumentException("생년월일은 현재 날짜보다 과거여야합니다.");
    }

}
