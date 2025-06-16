package com.silvertown.resident_service.resident.domain;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;
import com.silvertown.resident_service.person.domain.model.Person;
import com.silvertown.resident_service.residence.domain.model.EmergencyContact;
import com.silvertown.resident_service.residence.domain.model.Residence;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Resident {
    private int residentId;
    private Person person;
    private Residence residence;
    private List<EmergencyContact> emergencyContacts;
    private Date moveInDate;
    private Date moveOutDate;
    private ResidentStatus residentStatus;
    private String notes;

    public Resident(Person person, Residence residence, List<EmergencyContact> emergencyContacts, Date moveInDate, Date moveOutDate, String notes) {
        if (person == null) throw CustomError.of(ErrorCode.RESIDENT_BAD_REQUEST);
        if (residence == null) throw CustomError.of(ErrorCode.RESIDENT_BAD_REQUEST);
        if (emergencyContacts == null) throw CustomError.of(ErrorCode.RESIDENT_BAD_REQUEST);
        this.person = person;
        this.residence = residence;
        this.emergencyContacts = emergencyContacts;
        this.moveInDate = moveInDate;
        this.moveOutDate = moveOutDate;
        this.residentStatus = ResidentStatus.PENDING;
        this.notes = notes;
    }

    public void changeStatusTo(ResidentStatus status) {
        if (this.residentStatus.canTransitionTo(status)) {
            this.residentStatus = status;
        }
    }
}
