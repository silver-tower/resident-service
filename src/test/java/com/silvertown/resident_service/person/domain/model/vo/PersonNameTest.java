package com.silvertown.resident_service.person.domain.model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserName")
class PersonNameTest {
        @Test
        @DisplayName("UserName은 null일 수 없다\n")
        void shouldNotEmpty() {
            String userName = "";
            assertThrows(IllegalArgumentException.class, () -> new PersonName(userName));
        }
}