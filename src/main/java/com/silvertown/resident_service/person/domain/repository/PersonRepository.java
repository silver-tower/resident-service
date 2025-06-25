package com.silvertown.resident_service.person.domain.repository;

import com.silvertown.resident_service.common.vo.Phone;
import com.silvertown.resident_service.person.domain.model.Person;

public interface PersonRepository {
    boolean existsByPhoneNumber(Phone phone);
    Person save(Person person);
}
