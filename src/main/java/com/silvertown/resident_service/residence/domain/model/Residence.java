package com.silvertown.resident_service.residence.domain.model;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;
import com.silvertown.resident_service.residence.domain.model.vo.BuildingName;
import com.silvertown.resident_service.residence.domain.model.vo.Floor;
import com.silvertown.resident_service.residence.domain.model.vo.RoomLine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Residence {

    private int id;
    private BuildingName buildingName;
    private Floor floor;
    private RoomLine roomLine;
    private String notes;
    private ResidenceStatus status;

    public Residence(BuildingName buildingName, Floor floor, RoomLine roomLine, String notes) {
        if (buildingName == null) throw CustomError.of(ErrorCode.RESIDENCE_BAD_REQUEST);
        if (floor == null) throw CustomError.of(ErrorCode.RESIDENCE_BAD_REQUEST);
        if (roomLine == null) throw CustomError.of(ErrorCode.RESIDENCE_BAD_REQUEST);
        this.buildingName = buildingName;
        this.floor = floor;
        this.roomLine = roomLine;
        this.notes = notes;
        this.status = ResidenceStatus.INACTIVE;
    }

    public void changeStatusTo(ResidenceStatus status) {
        if (this.status.canTransitionTo(status)) {
            this.status = status;
        }
    }
}
