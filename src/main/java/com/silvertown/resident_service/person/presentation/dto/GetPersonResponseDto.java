package com.silvertown.resident_service.person.presentation.dto;

import com.silvertown.resident_service.person.domain.model.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class GetPersonResponseDto {
    int personId;
    String name;
    LocalDate birthDate;
    String phoneNumber;
    String email;
    String gender;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public GetPersonResponseDto(Person person) {
        this.personId = person.getId();
        this.name = person.getName().name();
        this.birthDate = person.getBirthDate().date();
        this.phoneNumber = person.getPhoneNumber().phone();
        this.email = person.getEmail() == null ? null : person.getEmail().value();
        this.gender = person.getGender().getValue();
        this.status = person.getStatus().name();
        this.createdAt = person.getCreatedAt();
        this.updatedAt = person.getUpdatedAt();
    }
}
