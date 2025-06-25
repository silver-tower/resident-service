package com.silvertown.resident_service.person.application.command;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;
import com.silvertown.resident_service.common.vo.Email;
import com.silvertown.resident_service.common.vo.Name;
import com.silvertown.resident_service.common.vo.Phone;
import com.silvertown.resident_service.person.domain.model.Gender;
import com.silvertown.resident_service.person.domain.model.vo.BirthDate;

public record CreatePersonCommand(Name name, BirthDate birthDate, Phone phoneNumber, Email email, Gender gender) {
    public CreatePersonCommand {
        if (name == null) throw CustomError.of(ErrorCode.USER_INVALID_DATA);
        if (birthDate == null) throw CustomError.of(ErrorCode.USER_INVALID_DATA);
        if (phoneNumber == null) throw CustomError.of(ErrorCode.USER_INVALID_DATA);
        if (gender == null) throw CustomError.of(ErrorCode.USER_INVALID_DATA);
    }
}
