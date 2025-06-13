package com.silvertown.resident_service.resident.domain;

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
        if (person == null) throw new IllegalArgumentException("");
        if (residence == null) throw new IllegalArgumentException("");
        if (emergencyContacts == null) throw new IllegalArgumentException("");
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
