package com.silvertown.resident_service.person.domain.model;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;
import com.silvertown.resident_service.person.domain.model.vo.BirthDate;
import com.silvertown.resident_service.common.vo.Email;
import com.silvertown.resident_service.common.vo.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Person {
    private int id;
    private Name name;
    private BirthDate birthDate;
    private PersonStatus status;
    private Gender gender;
    private Email email;

    public Person(Name name, BirthDate birthDate, Gender gender) {
        if (name == null) throw CustomError.of(ErrorCode.USER_INPUT_INVALID);
        if (birthDate == null) throw CustomError.of(ErrorCode.USER_INPUT_INVALID);
        if (gender == null) throw CustomError.of(ErrorCode.USER_INPUT_INVALID);
        this.status = PersonStatus.ACTIVE;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public void changeStatusTo(PersonStatus status) {
        if(this.status.canTransitionTo(status)) {
            this.status = status;
        }
    }
}
