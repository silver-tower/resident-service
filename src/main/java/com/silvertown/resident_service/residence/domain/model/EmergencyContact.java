package com.silvertown.resident_service.residence.domain.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmergencyContact {
    private int contactId;
    private int residenceId;
    private String contactName;
    private String phoneNumber;
    private String relation;
    private String note;

    public EmergencyContact(int residenceId, String contactName, String phoneNumber, String relation, String note) {
        if (contactName == null) throw new IllegalArgumentException("");
        if (phoneNumber == null) throw new IllegalArgumentException("");
        if (relation == null) throw new IllegalArgumentException("");
        this.residenceId = residenceId;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.relation = relation;
        this.note = note;
    }
}
