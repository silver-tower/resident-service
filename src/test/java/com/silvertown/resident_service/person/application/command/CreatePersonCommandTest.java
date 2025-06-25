package com.silvertown.resident_service.person.application.command;

import com.silvertown.resident_service.common.vo.Email;
import com.silvertown.resident_service.common.vo.Name;
import com.silvertown.resident_service.common.vo.Phone;
import com.silvertown.resident_service.person.domain.model.Gender;
import com.silvertown.resident_service.person.domain.model.vo.BirthDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CreatePersonCommand")
class CreatePersonCommandTest {

    @Test
    @DisplayName("CreatePersonCommand 생성 및 필드 값 확인 테스트")
    void shouldCreateCommandAndMatchFields() {
        Name name = new Name("홍길동");
        BirthDate birthDate = new BirthDate(LocalDate.of(2000, 1, 1));
        Phone phoneNumber = new Phone("01012345678");
        Email email = new Email("test@example.com");
        Gender gender = Gender.MALE;

        CreatePersonCommand command = new CreatePersonCommand(name, birthDate, phoneNumber, email, gender);

        assertNotNull(command, "command값 null");
        assertEquals(name, command.name(), "name 값 불일치 {} : {}" + name + command.name());
        assertEquals(birthDate, command.birthDate(), "birthDate 값 불일치 {} : {}" + birthDate + command.birthDate());
        assertEquals(phoneNumber, command.phoneNumber(), "phoneNumber 불일치 {} : {}" + phoneNumber + command.phoneNumber());
        assertEquals(email, command.email(), "email 불일치 {} : {}" + email + command.email());
        assertEquals(gender, command.gender(), "gender 불일치 {} : {}" + gender + gender.name());
    }

    @Test
    @DisplayName("CreatePersonCommand 생성 시 필수 필드 누락 확인 테스트")
    void shouldThrowExceptionWhenRequiredFieldsAreMissing() {
        Name name = new Name("홍길동");
        Phone phoneNumber = new Phone("01012345678");
        BirthDate birthDate = new BirthDate(LocalDate.of(2000, 1, 1));
        Email email = new Email("test@example.com");
        Gender gender = Gender.MALE;

        assertThrows(Exception.class, () -> new CreatePersonCommand(null, birthDate, phoneNumber, email, gender));
        assertThrows(Exception.class, () -> new CreatePersonCommand(name, null, phoneNumber, email, gender));
        assertThrows(Exception.class, () -> new CreatePersonCommand(name, birthDate, null, email, gender));
        assertThrows(Exception.class, () -> new CreatePersonCommand(name, birthDate, phoneNumber, email, null));
    }

    @Test
    @DisplayName("CreatePersonCommand 각 필드 값이 유효하지 않은 경우 테스트")
    void shouldThrowExceptionForInvalidFieldValues() {
        Gender gender = Gender.MALE;
        BirthDate birthDate = new BirthDate(LocalDate.of(2000, 1, 1));
        assertThrows(Exception.class, () -> new CreatePersonCommand( new Name(""), birthDate, new Phone("010-1234-5678"), new Email("test@example.com"), gender));
        assertThrows(Exception.class, () -> new CreatePersonCommand(new Name("홍길동"), birthDate, new Phone("112"), new Email("test@example.com"), gender));
        assertThrows(Exception.class, () -> new CreatePersonCommand(new Name("홍길동"), birthDate, new Phone("010-1234-5678"), new Email("invalid-email"), gender));
    }
}