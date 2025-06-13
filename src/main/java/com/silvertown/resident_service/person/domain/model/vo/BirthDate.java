package com.silvertown.resident_service.person.domain.model.vo;

import java.util.Date;

public record BirthDate(Date date) {
    public BirthDate {
        if (date == null) throw new IllegalArgumentException("생년월일을 입력해주세요.");
        Date now = new Date();
        if (!date.before(now)) throw new IllegalArgumentException("생년월일은 현재 날짜보다 과거여야합니다.");
    }
}
