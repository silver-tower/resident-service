package com.silvertown.resident_service.person.domain.model;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum Gender {
    MALE("M"), FEMALE("F"), NON_BINARY("U");
    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public static Gender of(String value){
        if (value == null) throw CustomError.of(ErrorCode.USER_INVALID_DATA);
        for(Gender gender : Gender.values()){
            if(gender.value.equals(value)){
                return gender;
            }
        }
        throw CustomError.of(ErrorCode.USER_BAD_REQUEST);
    }


}
