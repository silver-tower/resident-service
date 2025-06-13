package com.silvertown.resident_service.person.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Gender")
class GenderTest {

    @DisplayName("Gender는 null일 수 없다")
    @Test
    void genderShouldNotBeNull() {
        assertThrows(NullPointerException.class, () -> Gender.valueOf(null));
    }

    @DisplayName("Gender는 빈 문자열일 수 없다")
    @Test
    void genderShouldNotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Gender.valueOf(""));
    }
}
