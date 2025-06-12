package com.silvertown.resident_service.person.domain.model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LoginId")
class LoginIdTest {

    @Test
    @DisplayName("LoginId는 빈 문자열일 수 없다")
    void loginIdShouldNotBeEmpty() {
        String loginId = "";
        assertThrows(IllegalArgumentException.class, () -> new LoginId(loginId));
    }

    @Test
    @DisplayName("LoginId는 공백 문자열일 수 없다")
    void loginIdShouldNotBeBlank() {
        String loginId = " ";
        assertThrows(IllegalArgumentException.class, () -> new LoginId(loginId));
    }

    @Test
    @DisplayName("LoginId는 최대 길이를 초과할 수 없다")
    void loginIdShouldBeShorterThanMaxLength() {
        String loginId = "A".repeat(LoginId.MAX_LENGTH + 1);
        assertThrows(IllegalArgumentException.class, () -> new LoginId(loginId));
    }

    @Test
    @DisplayName("LoginId는 최소 길이보다 짧을 수 없다")
    void loginIdShouldBeLongerThanMinLength() {
        String loginId = "A".repeat(LoginId.MIN_LENGTH - 1);
        assertThrows(IllegalArgumentException.class, () -> new LoginId(loginId));
    }

    @Test
    @DisplayName("LoginId는 공백을 포함할 수 없다")
    void loginIdShouldNotContainSpace() {
        char[] chars = new char[LoginId.MAX_LENGTH];
        Arrays.fill(chars, 'A');
        chars[1] = ' '; // 공백 추가
        String loginId = Arrays.toString(chars);
        assertThrows(IllegalArgumentException.class, () -> new LoginId(loginId));
    }

    @Test
    @DisplayName("LoginId는 문자와 숫자로만 이루어져야 한다")
    void loginIdShouldContainOnlyAlphanumeric() {
        char[] chars = new char[LoginId.MAX_LENGTH];
        Arrays.fill(chars, 'A');
        chars[1] = '/'; // 알파벳/숫자가 아닌 문자를 추가
        String loginId = Arrays.toString(chars);
        assertThrows(IllegalArgumentException.class, () -> new LoginId(loginId));
    }
}