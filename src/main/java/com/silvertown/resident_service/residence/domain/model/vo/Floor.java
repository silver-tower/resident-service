package com.silvertown.resident_service.residence.domain.model.vo;

public record Floor(String floor) {
    static int MAX_LENGTH = 10;

    public Floor {
        if (floor == null) throw new IllegalArgumentException();
        if (floor.isBlank()) throw new IllegalArgumentException();
        if (floor.length() > MAX_LENGTH) throw new IllegalArgumentException();
    }
}
