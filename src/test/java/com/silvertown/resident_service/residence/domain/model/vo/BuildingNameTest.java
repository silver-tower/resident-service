package com.silvertown.resident_service.residence.domain.model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BuildingName")
class BuildingNameTest {

    @Test
    @DisplayName("유효한 BuildingName은 객체를 생성한다")
    void validBuildingNameShouldCreateObject() {
        String validName = "SkyTower";

        BuildingName buildingName = new BuildingName(validName);

        assertNotNull(buildingName);
        assertEquals(validName, buildingName.name());
    }

    @Test
    @DisplayName("빈 문자열 BuildingName은 예외를 발생시킨다")
    void emptyBuildingNameShouldThrowException() {
        String emptyName = "";

        assertThrows(IllegalArgumentException.class, () -> new BuildingName(emptyName));
    }

    @Test
    @DisplayName("공백 문자로만 이루어진 BuildingName은 예외를 발생시킨다")
    void blankBuildingNameShouldThrowException() {
        String blankName = "   ";

        assertThrows(IllegalArgumentException.class, () -> new BuildingName(blankName));
    }

    @Test
    @DisplayName("최대 길이를 초과한 BuildingName은 예외를 발생시킨다")
    void buildingNameExceedingMaxLengthShouldThrowException() {
        String tooLongName = "ThisBuildingNameIsTooLong";

        assertThrows(IllegalArgumentException.class, () -> new BuildingName(tooLongName));
    }
}
