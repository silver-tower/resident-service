package com.silvertown.resident_service.residence.domain.model;

import com.silvertown.resident_service.residence.domain.model.vo.BuildingName;
import com.silvertown.resident_service.residence.domain.model.vo.Floor;
import com.silvertown.resident_service.residence.domain.model.vo.RoomLine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Residence")
class ResidenceTest {

    @Test
    @DisplayName("유효한 Residence 객체를 생성한다")
    void validResidenceShouldCreateObject() {
        BuildingName buildingName = new BuildingName("112");
        Floor floor = new Floor("5");
        RoomLine roomLine = new RoomLine("2");
        String notes = "Accessible for disabled residents";

        Residence residence = new Residence(buildingName, floor, roomLine, notes);

        assertNotNull(residence);
        assertEquals(buildingName, residence.getBuildingName());
        assertEquals(floor, residence.getFloor());
        assertEquals(roomLine, residence.getRoomLine());
        assertEquals(notes, residence.getNotes());
        assertEquals(ResidenceStatus.INACTIVE, residence.getStatus());
    }

    @Test
    @DisplayName("BuildingName이 null일 경우 예외를 발생시킨다")
    void residenceShouldThrowExceptionIfBuildingNameIsNull() {
        Floor floor = new Floor("5");
        RoomLine roomLine = new RoomLine("2");
        String notes = "Test note";

        assertThrows(IllegalArgumentException.class, () -> new Residence(null, floor, roomLine, notes));
    }

    @Test
    @DisplayName("Floor가 null일 경우 예외를 발생시킨다")
    void residenceShouldThrowExceptionIfFloorIsNull() {
        BuildingName buildingName = new BuildingName("204");
        RoomLine roomLine = new RoomLine("2");
        String notes = "Test note";

        assertThrows(IllegalArgumentException.class, () -> new Residence(buildingName, null, roomLine, notes));
    }

    @Test
    @DisplayName("RoomLine이 null일 경우 예외를 발생시킨다")
    void residenceShouldThrowExceptionIfRoomLineIsNull() {
        BuildingName buildingName = new BuildingName("3");
        Floor floor = new Floor("5");
        String notes = "Test note";

        assertThrows(IllegalArgumentException.class, () -> new Residence(buildingName, floor, null, notes));
    }

    @Test
    @DisplayName("Residence의 상태를 변경할 수 있다")
    void residenceShouldChangeStatus() {
        BuildingName buildingName = new BuildingName("A");
        Floor floor = new Floor("5");
        RoomLine roomLine = new RoomLine("3");
        String notes = "Test note";

        Residence residence = new Residence(buildingName, floor, roomLine, notes);
        residence.changeStatusTo(ResidenceStatus.PENDING);

        assertEquals(ResidenceStatus.PENDING, residence.getStatus());
    }

    @Test
    @DisplayName("Residence 상태를 올바르게 변경할 수 없으면 예외를 발생시키지 않는다(변경되지 않음)")
    void residenceShouldNotChangeStatusToInvalidTransition() {
        BuildingName buildingName = new BuildingName("102");
        Floor floor = new Floor("5");
        RoomLine roomLine = new RoomLine("5");
        String notes = "Test note";

        Residence residence = new Residence(buildingName, floor, roomLine, notes);
        residence.changeStatusTo(ResidenceStatus.DELETED);

        assertNotEquals(ResidenceStatus.DELETED, residence.getStatus());
        assertEquals(ResidenceStatus.INACTIVE, residence.getStatus());
    }
}
