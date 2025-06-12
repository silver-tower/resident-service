package com.silvertown.resident_service.person.domain.model;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("M"), FEMALE("F"), NON_BINARY("U");
    private final String value;

    Gender(String value) {
        this.value = value;
    }
}
