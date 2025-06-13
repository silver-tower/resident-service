package com.silvertown.resident_service.common.vo;

public record Name(String name) {
    static int MIN_LENGTH = 2;
    public Name {
        if (name.isEmpty()) throw new IllegalArgumentException("이름을 입력해주세요.");
        if (name.length() < MIN_LENGTH) throw new IllegalArgumentException("");
    }
}
