package com.silvertown.resident_service.person.domain.model.vo;


import com.silvertown.resident_service.common.vo.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Email")
class EmailTest {

    @Test
    @DisplayName("유효한 이메일은 객체를 생성한다")
    void validEmailShouldCreateObject() {
        String inputEmail = "test@example.com";

        Email email = new Email(inputEmail);

        assertEquals(inputEmail, email.value(), "Email 객체 내부 값은 입력값과 동일해야 합니다.");
    }

    @Test
    @DisplayName("잘못된 이메일은 예외를 던진다")
    void invalidEmailShouldThrowException() {
        String invalidEmail1 = "test@example";
        String invalidEmail2 = "test@example.";
        String invalidEmail3 = "test@.example";
        String invalidEmail4 = "@test.example";

        assertThrows(Exception.class, () -> new Email(invalidEmail1));
        assertThrows(Exception.class, () -> new Email(invalidEmail2));
        assertThrows(Exception.class, () -> new Email(invalidEmail3));
        assertThrows(Exception.class, () -> new Email(invalidEmail4));
    }

    @Test
    @DisplayName("Email은 null일 수 없다")
    void emailShouldNotBeNull() {
        String nullEmail = null;
        assertThrows(Exception.class, () -> new Email(nullEmail));
    }

    @Test
    @DisplayName("Email은 빈 문자열일 수 없다")
    void emailShouldNotBeEmpty() {
        String emptyEmail = "";
        assertThrows(Exception.class, () -> new Email(emptyEmail));
    }

    @Test
    @DisplayName("Email은 공백을 포함할 수 없다")
    void emailShouldNotIncludeBlank() {
        String includeBlankEmail = "sdfa sdf@abc.io";
        assertThrows(Exception.class, () -> new Email(includeBlankEmail));
    }
}
