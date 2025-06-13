package com.silvertown.resident.user.domain;

import java.util.Date;

public record Birth(Date date) {
    public Birth {
        if (date == null) throw new IllegalArgumentException("생년월일을 입력해주세요.");
        Date now = new Date();
        if (date.after(now)) throw new IllegalArgumentException("생년월일은 현재 날짜보다 이전일 수 없습니다.");
    }
}
