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
                .status(toDomainStatus(entity.getStatus()))
                .gender(toDomainGender(entity.getGender()))
                .email(entity.getEmail() == null ? null : new Email(entity.getEmail()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public PersonEntity toEntity(Person domain) {
        int personId = domain.getId();
        return PersonEntity.builder()
                .personId(personId == 0 ? null : personId)
                .name(domain.getName().name())
                .birthDate(domain.getBirthDate().date())
                .phoneNumber(domain.getPhoneNumber().phone())
                .status(toEntityStatus(domain.getStatus()))
                .gender(toEntityGender(domain.getGender()))
                .email(domain.getEmail() == null ? null : domain.getEmail().value())
                .build();
    }

    public PersonStatus toDomainStatus(PersonEntity.Status status) {
        return PersonStatus.valueOf(status.toString());
    }

    public PersonEntity.Status toEntityStatus(PersonStatus status) {
        return status == null ? null : PersonEntity.Status.valueOf(status.toString());
    }

    public Gender toDomainGender(PersonEntity.Gender gender) {
        return Gender.of(gender.toString());
    }

    public PersonEntity.Gender toEntityGender(Gender gender) {
        return PersonEntity.Gender.valueOf(gender.getValue());
    }

}
