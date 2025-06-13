package com.silvertown.resident_service.residence.domain.model.vo;

public record BuildingName(String name) {
    static int MAX_LENGTH = 10;

    public BuildingName {
        if (name.isEmpty()) throw new IllegalArgumentException("");
        if (name.isBlank()) throw new IllegalArgumentException("");
        if (name.length() > MAX_LENGTH) throw new IllegalArgumentException("");
    }
}
