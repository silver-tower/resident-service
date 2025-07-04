package com.silvertown.resident_service.person.presentation;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
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
import com.silvertown.resident_service.person.domain.model.PersonStatus;
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
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
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
                .andDo(document("create-user", resource(
                        ResourceSnippetParameters.builder()
                                .summary("회원생성")
                                .description("회원을 생성합니다.")
                                .responseFields(
                                        fieldWithPath("success").description("성공여부"),
                                        fieldWithPath("timestamp").description("응답시간"),
                                        fieldWithPath("data.personId").description("생성된 사용자 id"),
                                        fieldWithPath("uuid").description("요청 UUID")
                                )
                                .build())
                ));
    }

    @Test
    @DisplayName("GET /api/vi/users/{userId} - 정상적인 요청으로 사용자 조회")
    void shouldGetPersonSuccessfully() throws Exception {
        int personId = 1;
        Name name = new Name("John Doe");
        Phone phoneNumber = new Phone("01012345670");
        BirthDate birthDate = new BirthDate(LocalDate.of(2000, 1, 1));
        Email email = null;
        Gender gender = Gender.MALE;
        PersonStatus status = PersonStatus.ACTIVE;
        LocalDateTime createAt = LocalDateTime.now().minusDays(3);
        LocalDateTime updateAt = LocalDateTime.now().minusDays(2);
        Person mockPerson = Person.builder()
                .id(personId)
                .name(name)
                .phoneNumber(phoneNumber)
                .birthDate(birthDate)
                .email(email)
                .gender(gender)
                .status(status)
                .createdAt(createAt)
                .updatedAt(updateAt)
                .build();

        Mockito.when(personApplicationService.getPersonById(Mockito.any())).thenReturn(mockPerson);

        mockMvc.perform(get("/api/v1/users/{personId}", personId)
                        .contentType("application/json"))
                .andExpect(status().isOk()) // 응답 상태 코드 200 확인
                .andDo(document("get-user", resource(
                        ResourceSnippetParameters.builder()
                                .summary("사용자 조회 API")
                                .description("특정 ID를 기준으로 사용자를 조회합니다.")
                                .pathParameters(
                                        parameterWithName("personId").description("조회할 사용자 ID")
                                )
                                .responseFields(
                                        fieldWithPath("success").description("요청 성공 여부"),
                                        fieldWithPath("timestamp").description("응답 시간"),
                                        fieldWithPath("uuid").description("요청 식별 UUID"),
                                        fieldWithPath("data.personId").description("사용자 ID"),
                                        fieldWithPath("data.name").description("사용자 이름"),
                                        fieldWithPath("data.phoneNumber").description("사용자 전화번호"),
                                        fieldWithPath("data.birthDate").description("생년월일"),
                                        fieldWithPath("data.email").description("사용자 이메일 (nullable)"),
                                        fieldWithPath("data.gender").description("사용자의 성별"),
                                        fieldWithPath("data.status").description("사용자 상태"),
                                        fieldWithPath("data.createdAt").description("생성 시간"),
                                        fieldWithPath("data.updatedAt").description("수정 시간")
                                )
                                .build()))
                );

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
