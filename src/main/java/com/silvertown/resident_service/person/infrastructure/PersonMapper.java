package com.silvertown.resident_service.person.infrastructure;

import com.silvertown.resident_service.common.vo.Email;
import com.silvertown.resident_service.common.vo.Name;
import com.silvertown.resident_service.common.vo.Phone;
import com.silvertown.resident_service.person.domain.model.Gender;
import com.silvertown.resident_service.person.domain.model.Person;
import com.silvertown.resident_service.person.domain.model.PersonStatus;
import com.silvertown.resident_service.person.domain.model.vo.BirthDate;
import com.silvertown.resident_service.person.infrastructure.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public Person toDomain(PersonEntity entity) {
        return Person.builder()
                .id(entity.getPersonId())
                .name(new Name(entity.getName()))
                .birthDate(new BirthDate(entity.getBirthDate()))
                .phoneNumber(new Phone(entity.getPhoneNumber()))
                .status(PersonStatus.valueOf(entity.getStatus().toString()))
                .gender(Gender.of(entity.getGender().toString()))
                .email(entity.getEmail() == null ? null : new Email(entity.getEmail()))
                .build();
    }

    public PersonEntity toEntity(Person domain) {
        int personId = domain.getId();
        PersonEntity entity =  PersonEntity.builder()
                .personId(personId == 0 ? null : personId)
                .name(domain.getName().name())
                .birthDate(domain.getBirthDate().date())
                .phoneNumber(domain.getPhoneNumber().phone())
                .status(domain.getStatus() == null ? null : PersonEntity.Status.valueOf(domain.getStatus().toString()))
                .gender(PersonEntity.Gender.valueOf(domain.getGender().getValue()))
                .email(domain.getEmail() == null ? null : domain.getEmail().value())
                .build();
        return entity;
    }


}
