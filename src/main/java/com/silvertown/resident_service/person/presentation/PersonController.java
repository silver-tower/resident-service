package com.silvertown.resident_service.person.presentation;

import com.silvertown.resident_service.common.vo.Email;
import com.silvertown.resident_service.common.vo.Name;
import com.silvertown.resident_service.common.vo.Phone;
import com.silvertown.resident_service.person.application.command.CreatePersonCommand;
import com.silvertown.resident_service.person.application.query.GetPersonQuery;
import com.silvertown.resident_service.person.application.service.PersonApplicationService;
import com.silvertown.resident_service.person.domain.model.Gender;
import com.silvertown.resident_service.person.domain.model.Person;
import com.silvertown.resident_service.person.domain.model.vo.BirthDate;
import com.silvertown.resident_service.person.presentation.dto.CreatePersonRequestDto;
import com.silvertown.resident_service.person.presentation.dto.CreatePersonResponseDto;
import com.silvertown.resident_service.person.presentation.dto.GetPersonResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class PersonController {

    private final PersonApplicationService personApplicationService;

    @PostMapping("/api/v1/users")
    public CreatePersonResponseDto createPerson(@RequestBody CreatePersonRequestDto request) {
        CreatePersonCommand command = new CreatePersonCommand(
                new Name(request.getName()),
                new BirthDate(request.getBirthDate()),
                new Phone(request.getPhoneNumber()),
                request.getEmail() == null ? null : new Email(request.getEmail()),
                Gender.of(request.getGender().toUpperCase())
        );
        Person person = personApplicationService.createPerson(command);
        return new CreatePersonResponseDto(person.getId());
    }


    @GetMapping("/api/v1/users/{personId}")
    public GetPersonResponseDto getPersonById(@PathVariable int personId) {
        GetPersonQuery query = new GetPersonQuery(personId);
        Person person = personApplicationService.getPersonById(query);
        return new GetPersonResponseDto(person);
    }
}
