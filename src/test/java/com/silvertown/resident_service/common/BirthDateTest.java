package com.silvertown.resident_service.common;

import com.silvertown.resident_service.person.domain.model.vo.BirthDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BirthDate")
class BirthDateTest {

    @Test
    @DisplayName("BirthDate는 null일 수 없다")
    void birthDateShouldNotBeNull() {
        Date nullDate = null;
        assertThrows(IllegalArgumentException.class, () -> new BirthDate(nullDate));
    }

    @Test
    @DisplayName("BirthDate는 오늘 이후의 날짜일 수 없다")
    void birthDateShouldNotBeAfterToday() {
        Date today = new Date();
        assertThrows(IllegalArgumentException.class, () -> new BirthDate(today));
    }

}