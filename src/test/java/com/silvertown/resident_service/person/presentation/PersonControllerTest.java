package com.silvertown.resident_service.person.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silvertown.resident_service.common.vo.Email;
import com.silvertown.resident_service.common.vo.Name;
import com.silvertown.resident_service.common.vo.Phone;
import com.silvertown.resident_service.person.application.service.PersonApplicationService;
import com.silvertown.resident_service.person.domain.model.Gender;
import com.silvertown.resident_service.person.domain.model.Person;
import com.silvertown.resident_service.person.domain.model.vo.BirthDate;
import com.silvertown.resident_service.person.presentation.dto.CreatePersonRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonApplicationService personApplicationService;

    @Qualifier("jacksonObjectMapper")
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/v1/users - 정상적인 요청으로 사용자 생성")
    void shouldCreatePersonSuccessfully() throws Exception {
        CreatePersonRequestDto requestDto = new CreatePersonRequestDto(
                "John Doe",
                "1990-01-01",
                "01012345670",
                null,
                "M"
        );
        Name name = new Name(requestDto.getName());
        Phone phoneNumber = new Phone(requestDto.getPhoneNumber());
        BirthDate birthDate = new BirthDate(requestDto.getBirthDate());
        Email email = null == requestDto.getEmail() ? null : new Email(requestDto.getEmail());
        Gender gender = Gender.MALE;
        Person mockPerson = Person.builder()
                .id(1)
                .name(name)
                .phoneNumber(phoneNumber)
                .birthDate(birthDate)
                .email(email)
                .gender(gender)
                .build();

        Mockito.when(personApplicationService.createPerson(Mockito.any())).thenReturn(mockPerson);

        mockMvc.perform(post("/api/v1/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.personId").value("1"));
    }
}