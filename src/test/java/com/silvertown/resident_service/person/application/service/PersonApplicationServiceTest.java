package com.silvertown.resident_service.person.application.service;

import com.silvertown.resident_service.common.vo.Email;
import com.silvertown.resident_service.common.vo.Name;
import com.silvertown.resident_service.common.vo.Phone;
import com.silvertown.resident_service.person.application.command.CreatePersonCommand;
import com.silvertown.resident_service.person.application.query.GetPersonQuery;
import com.silvertown.resident_service.person.domain.model.Gender;
import com.silvertown.resident_service.person.domain.model.Person;
import com.silvertown.resident_service.person.domain.model.PersonStatus;
import com.silvertown.resident_service.person.domain.model.vo.BirthDate;
import com.silvertown.resident_service.person.domain.repository.PersonRepository;
import com.silvertown.resident_service.person.domain.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("PersonApplicationService")
class PersonApplicationServiceTest {

    @Mock
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonApplicationService personApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("createPerson - 중복된 전화번호 예외 발생 테스트")
    void shouldThrowExceptionWhenPhoneNumberIsDuplicated() {
        Phone testPhone = new Phone("01012345678");
        CreatePersonCommand command = new CreatePersonCommand(
                new Name("홍길동"),
                new BirthDate(LocalDate.of(2000, 1, 1)),
                testPhone,
                new Email("test@example.com"),
                Gender.MALE
        );

        when(personService.isDuplicatedPhone(testPhone)).thenReturn(true);

        assertThrows(Exception.class, () -> personApplicationService.createPerson(command));
        verify(personService, times(1)).isDuplicatedPhone(testPhone);
        verify(personRepository, never()).save(any(Person.class));
    }

    @Test
    @DisplayName("createPerson - 정상적으로 사용자 생성 테스트")
    void shouldCreatePersonSuccessfully() {
        Phone testPhone = new Phone("01012345678");
        CreatePersonCommand command = new CreatePersonCommand(
                new Name("홍길동"),
                new BirthDate(LocalDate.of(2000, 1, 1)),
                testPhone,
                new Email("test@example.com"),
                Gender.MALE
        );

        when(personService.isDuplicatedPhone(testPhone)).thenReturn(false);
        Person mockPerson = Person.builder()
                .name(command.name())
                .phoneNumber(command.phoneNumber())
                .email(command.email())
                .gender(command.gender())
                .build();
        when(personRepository.save(any(Person.class))).thenReturn(mockPerson);

        Person createdPerson = personApplicationService.createPerson(command);

        assertNotNull(createdPerson);
        assertEquals(command.name(), createdPerson.getName());
        assertEquals(command.phoneNumber(), createdPerson.getPhoneNumber());
        assertEquals(command.email(), createdPerson.getEmail());
        assertEquals(command.gender(), createdPerson.getGender());

        verify(personService, times(1)).isDuplicatedPhone(testPhone);
        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    @DisplayName("getPersonById - 사용자 존재 시 정상 반환")
    void shouldReturnPersonWhenExists() {
        int personId = 1;
        GetPersonQuery query = new GetPersonQuery(personId);

        Person mockPerson = Person.builder()
                .id(personId)
                .name(new Name("홍길동"))
                .birthDate(new BirthDate(LocalDate.of(1985, 1, 1)))
                .phoneNumber(new Phone("01012345678"))
                .status(PersonStatus.ACTIVE)
                .gender(Gender.MALE)
                .build();

        when(personRepository.findById(personId)).thenReturn(Optional.of(mockPerson));

        Person result = personApplicationService.getPersonById(query);

        assertNotNull(result);
        assertEquals(mockPerson.getId(), result.getId());
        assertEquals(mockPerson.getName(), result.getName());
        assertEquals(mockPerson.getPhoneNumber(), result.getPhoneNumber());

        verify(personRepository, times(1)).findById(personId);
    }

    @Test
    @DisplayName("getPersonById - 사용자 없으면 예외 발생")
    void shouldThrowExceptionWhenPersonNotFound() {
        int personId = 999;
        GetPersonQuery query = new GetPersonQuery(personId);

        when(personRepository.findById(personId)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> personApplicationService.getPersonById(query));

        verify(personRepository, times(1)).findById(personId);
    }

}