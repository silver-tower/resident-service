package com.silvertown.resident_service.person.domain.model.vo;

public record LoginId(String loginId) {
    static int MAX_LENGTH = 10;
    static int MIN_LENGTH = 5;

    public LoginId {
        if (loginId.isEmpty()) throw new IllegalArgumentException("로그인 아이디를 입력해주세요.");
        if (loginId.contains(" ")) throw new IllegalArgumentException("로그인 아이디는 공백이 포함될 수 없습니다.");
        if (loginId.length() > LoginId.MAX_LENGTH) throw new IllegalArgumentException("로그인 아이디는 10자 이하로 입력해주세요.");
        if (loginId.length() < LoginId.MIN_LENGTH) throw new IllegalArgumentException("로그인 아이디는 3자 이상 입력해주세요.");
        if (!loginId.matches("^[a-zA-Z0-9]*$")) throw new IllegalArgumentException("로그인 아이디는 영문자와 숫자만 입력해주세요.");
    }
}
