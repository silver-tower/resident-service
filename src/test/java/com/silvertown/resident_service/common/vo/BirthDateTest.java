package com.silvertown.resident_service.common.vo;

import com.silvertown.resident_service.person.domain.model.vo.BirthDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BirthDate")
class BirthDateTest {

    @Test
    @DisplayName("BirthDate는 null일 수 없다")
    void birthDateShouldNotBeNull() {
        LocalDate nullDate = null;
        assertThrows(Exception.class, () -> new BirthDate(nullDate));
    }

    @Test
    @DisplayName("BirthDate는 오늘 이후의 날짜일 수 없다")
    void birthDateShouldNotBeAfterToday() {
        LocalDate today = LocalDate.now();
        assertThrows(Exception.class, () -> new BirthDate(today));
    }

    @Test
    @DisplayName("BirthDate 객체를 정상적으로 생성할 수 있다")
    void canCreateBirthDateObject() {
        String validDate = "1999-01-01";
        LocalDate validLocalDate = LocalDate.parse(validDate);
        BirthDate birthDate = new BirthDate(validLocalDate);
        assertNotNull(birthDate, "BirthDate 객체가 null입니다.");
    }

}
