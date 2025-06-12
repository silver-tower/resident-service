package com.silvertown.resident_service.person.domain.model.vo;

public record PersonName(String name) {
    public PersonName {
        if (name.isEmpty()) throw new IllegalArgumentException("이름을 입력해주세요.");
    }
}
