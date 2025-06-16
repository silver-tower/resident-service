package com.silvertown.resident_service.residence.domain.model;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EmergencyContact {
    private int contactId;
    private int residenceId;
    private String contactName;
    private String phoneNumber;
    private String relation;
    private String note;

    public EmergencyContact(int residenceId, String contactName, String phoneNumber, String relation, String note) {
        if (contactName == null) throw CustomError.of(ErrorCode.EMERGENCY_CONTACT_BAD_REQUEST);
        if (phoneNumber == null) throw CustomError.of(ErrorCode.EMERGENCY_CONTACT_BAD_REQUEST);
        if (relation == null) throw CustomError.of(ErrorCode.EMERGENCY_CONTACT_BAD_REQUEST);
        this.residenceId = residenceId;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.relation = relation;
        this.note = note;
    }
}
