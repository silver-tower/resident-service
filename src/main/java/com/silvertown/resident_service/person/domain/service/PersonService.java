package com.silvertown.resident_service.person.domain.service;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;
import com.silvertown.resident_service.common.vo.Phone;
import com.silvertown.resident_service.person.domain.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public boolean isDuplicatedPhone(Phone phone) {
        if (phone == null) return false;
        try {
            return personRepository.existsByPhoneNumber(phone);
        } catch (Exception e) {
            throw CustomError.of(ErrorCode.INTERNAL_SERVER_ERROR, e);
        }
    }
}
