package com.silvertown.resident_service.common.vo;

public record Phone(String phone) {
    private static final String PHONE_REGEX = "^01(?:0|1|[6-9])(?:\\d{8}|\\d{7}$)";
    static int MAX_LENGTH = 11;
    static int MIN_LENGTH = 10;

    public Phone {
        if (phone == null || phone.isBlank()) throw new IllegalArgumentException("");
        if (phone.length() > MAX_LENGTH) throw new IllegalArgumentException("");
        if (phone.length() < MIN_LENGTH) throw new IllegalArgumentException("");
        if (!phone.matches(PHONE_REGEX)) throw new IllegalArgumentException("");
    }
}
