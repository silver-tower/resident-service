package com.silvertown.resident_service.common.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Phone")
class PhoneTest {

    @Test
    @DisplayName("유효한 전화번호는 Phone 객체를 생성한다")
    void validPhoneShouldCreateObject() {
        String validPhone = "01012345678";

        Phone phone = new Phone(validPhone);

        assertNotNull(phone);
        assertEquals(validPhone, phone.phone());
    }

    @Test
    @DisplayName("null 전화번호는 예외를 발생시킨다")
    void nullPhoneShouldThrowException() {
        String nullPhone = null;

        assertThrows(IllegalArgumentException.class, () -> new Phone(nullPhone));
    }

    @Test
    @DisplayName("공백 전화번호는 예외를 발생시킨다")
    void blankPhoneShouldThrowException() {
        String blankPhone = "   ";

        assertThrows(IllegalArgumentException.class, () -> new Phone(blankPhone));
    }

    @Test
    @DisplayName("최대 길이를 초과한 전화번호는 예외를 발생시킨다")
    void phoneExceedingMaxLengthShouldThrowException() {
        String tooLongPhone = "010123456789"; // 12자리

        assertThrows(IllegalArgumentException.class, () -> new Phone(tooLongPhone));
    }

    @Test
    @DisplayName("최소 길이를 벗어난 전화번호는 예외를 발생시킨다")
    void phoneBelowMinLengthShouldThrowException() {
        String tooShortPhone = "010123"; // 6자리

        assertThrows(IllegalArgumentException.class, () -> new Phone(tooShortPhone));
    }

    @Test
    @DisplayName("올바르지 않은 형식의 전화번호는 예외를 발생시킨다")
    void invalidFormatPhoneShouldThrowException() {
        String invalidPhone = "02012345678"; // "02"로 시작

        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }
}