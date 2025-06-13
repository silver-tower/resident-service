package com.silvertown.resident_service.common.vo;

public record Email(String value) {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public Email {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email must not be null or empty");
        }
        if (!isValidEmail(value)) {
            throw new IllegalArgumentException("Invalid email format: " + value);
        }
    }

    private static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }
}
