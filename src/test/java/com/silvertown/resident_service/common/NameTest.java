package com.silvertown.resident_service.common;

import com.silvertown.resident_service.common.vo.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserName")
class NameTest {
        @Test
        @DisplayName("UserName은 null일 수 없다")
        void shouldNotEmpty() {
            String userName = "";
            assertThrows(IllegalArgumentException.class, () -> new Name(userName));
        }

    @Test
    @DisplayName("UserName은 최소 길이보다 길어야 한다")
    void userNameShouldBeLongerThanMinLength() {
        String userName = "김";
        assertThrows(IllegalArgumentException.class, () -> new Name(userName));
    }
}