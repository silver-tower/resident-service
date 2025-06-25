package com.silvertown.resident_service.person.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;


@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePersonRequestDto {

    private String name;
    private String birthDate;
    private String phoneNumber;
    private String email;
    private String gender;

    public LocalDate getBirthDate(){
        return LocalDate.parse(birthDate);
    }

}
