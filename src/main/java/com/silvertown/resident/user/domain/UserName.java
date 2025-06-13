package com.silvertown.resident.user.domain;

public record UserName(String name) {
    public UserName {
        if (name.isEmpty()) throw new IllegalArgumentException("이름을 입력해주세요.");
    }
}
