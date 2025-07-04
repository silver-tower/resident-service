package com.silvertown.resident_service.person.application.service;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;
import com.silvertown.resident_service.person.application.command.CreatePersonCommand;
import com.silvertown.resident_service.person.application.query.GetPersonQuery;
import com.silvertown.resident_service.person.domain.model.Person;
import com.silvertown.resident_service.person.domain.repository.PersonRepository;
import com.silvertown.resident_service.person.domain.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonApplicationService {

    private final PersonService personService;
    private final PersonRepository personRepository;

    public Person createPerson(CreatePersonCommand command) {
        boolean isExist = personService.isDuplicatedPhone(command.phoneNumber());
        if (isExist) throw CustomError.of(ErrorCode.USER_DUPLICATE);
        Person person = Person.builder()
                .name(command.name())
                .phoneNumber(command.phoneNumber())
                .email(command.email())
                .gender(command.gender())
                .birthDate(command.birthDate())
                .build();
        return personRepository.save(person);
    }

    public Person getPersonById(GetPersonQuery query) {
        return personRepository.findById(query.personId()).orElseThrow(() -> CustomError.of(ErrorCode.USER_NOT_FOUND));
    }
}
