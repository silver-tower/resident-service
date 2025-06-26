package com.silvertown.resident_service.person.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silvertown.resident_service.common.logging.RequestTrackingFilter;
import com.silvertown.resident_service.common.vo.Email;
import com.silvertown.resident_service.common.vo.Name;
import com.silvertown.resident_service.common.vo.Phone;
import com.silvertown.resident_service.config.JacksonConfig;
import com.silvertown.resident_service.docs.RestDocsTestSupport;
import com.silvertown.resident_service.person.application.service.PersonApplicationService;
import com.silvertown.resident_service.person.domain.model.Gender;
import com.silvertown.resident_service.person.domain.model.Person;
import com.silvertown.resident_service.person.domain.model.vo.BirthDate;
import com.silvertown.resident_service.person.presentation.dto.CreatePersonRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({RequestTrackingFilter.class, JacksonConfig.class})
@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest extends RestDocsTestSupport {

    @MockitoBean
    private PersonApplicationService personApplicationService;

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
                        .content(objectMapper
                                .writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.personId").value("1"))
                .andDo(document("users",
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공여부"),
                                fieldWithPath("timestamp").type(JsonFieldType.STRING).description("응답시간"),
                                fieldWithPath("data.personId").type(JsonFieldType.NUMBER).description("생성된 사용자 id"),
                                fieldWithPath("uuid").type(JsonFieldType.NULL).description("요청 UUID (test에서는 null)")
                        )
                ));
    }

    @TestConfiguration
    static class FilterConfig {
        @Bean
        public FilterRegistrationBean<RequestTrackingFilter> yourFilter() {
            FilterRegistrationBean<RequestTrackingFilter> registration = new FilterRegistrationBean<>();
            registration.setFilter(new RequestTrackingFilter());
            registration.addUrlPatterns("/*");
            return registration;
        }

    }
}