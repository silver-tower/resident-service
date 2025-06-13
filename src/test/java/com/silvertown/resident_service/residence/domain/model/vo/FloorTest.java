package com.silvertown.resident_service.residence.domain.model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Floor")
class FloorTest {

    @Test
    @DisplayName("유효한 Floor가 주어지면 객체가 생성된다")
    void validFloorShouldCreateObject() {
        String validFloor = "B1";

        Floor floor = new Floor(validFloor);

        assertNotNull(floor);
        assertEquals(validFloor, floor.floor());
    }

    @Test
    @DisplayName("null Floor는 예외를 발생시킨다")
    void nullFloorShouldThrowException() {
        String nullFloor = null;

        assertThrows(IllegalArgumentException.class, () -> new Floor(nullFloor));

    }

    @Test
    @DisplayName("공백 Floor는 예외를 발생시킨다")
    void blankFloorShouldThrowException() {
        String blankFloor = "   ";

        assertThrows(IllegalArgumentException.class, () -> new Floor(blankFloor));

    }

    @Test
    @DisplayName("최대 길이를 초과한 Floor는 예외를 발생시킨다")
    void floorExceedingMaxLengthShouldThrowException() {
        String tooLongFloor = "12345678901"; // 11자 입력

        assertThrows(IllegalArgumentException.class, () -> new Floor(tooLongFloor));

    }
}