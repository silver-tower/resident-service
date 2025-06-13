package com.silvertown.resident_service.residence.domain.model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RoomLine")
class RoomLineTest {

    @Test
    @DisplayName("유효한 RoomLine이 주어지면 객체가 생성된다")
    void validRoomLineShouldCreateObject() {
        String validLine = "A-101";

        RoomLine roomLine = new RoomLine(validLine);

        assertNotNull(roomLine);
        assertEquals(validLine, roomLine.line());
    }

    @Test
    @DisplayName("null RoomLine은 예외를 발생시킨다")
    void nullRoomLineShouldThrowException() {
        String nullLine = null;

        assertThrows(IllegalArgumentException.class, () -> new RoomLine(nullLine));
    }

    @Test
    @DisplayName("공백 RoomLine은 예외를 발생시킨다")
    void blankRoomLineShouldThrowException() {
        String blankLine = "   ";

        assertThrows(IllegalArgumentException.class, () -> new RoomLine(blankLine));
    }

    @Test
    @DisplayName("최대 길이를 초과한 RoomLine은 예외를 발생시킨다")
    void roomLineExceedingMaxLengthShouldThrowException() {
        String tooLongLine = "12345678901"; // 11자 입력

        assertThrows(IllegalArgumentException.class, () -> new RoomLine(tooLongLine));
    }
}
