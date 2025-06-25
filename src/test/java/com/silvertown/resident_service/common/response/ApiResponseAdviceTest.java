package com.silvertown.resident_service.common.response;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;
import com.silvertown.resident_service.common.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("ApiResponseAdvice")
@WebMvcTest(controllers = ApiResponseAdviceTest.TestController.class)
class ApiResponseAdviceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiResponseAdviceTest.class);
    @MockitoSpyBean
    private ApiResponseAdvice apiResponseAdvice;

    @MockitoSpyBean
    private GlobalExceptionHandler globalExceptionHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new TestController())
                .setControllerAdvice(apiResponseAdvice,globalExceptionHandler)
                .build();
    }

    @Test
    @DisplayName("beforeBodyWrite: body가 유효할 때 그대로 반환되는지 테스트")
    void shouldReturnValidBodyAsIs() throws Exception {
        MvcResult result = mockMvc.perform(get("/test/valid-body")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ApiResponse<?> response = objectMapper.readValue(
                result.getResponse().getContentAsString(), ApiResponse.class
        );
        assertNotNull(response, "ApiResponse 응답이 null이어서는 안 됩니다.");
        assertEquals(TestController.body, response.getData());
    }

    @Test
    @DisplayName("GlobalExceptionHandler에서 발생하는 예외는 ApiResponse로 래핑된다.")
    void shouldReturnErrorResponseWhenExceptionOccurs() throws Exception {
        MvcResult result = mockMvc.perform(get("/test/exception") // 예외를 발생시키는 엔드포인트
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isInternalServerError()) // 예외 발생 시 500 상태 코드 확인
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        log.info("Error Response: {}", responseBody);

        ApiResponse<?> response = objectMapper.readValue(responseBody, ApiResponse.class);

        assertNotNull(response, "ApiResponse 응답이 null이 아니어야 합니다.");
        assertFalse(response.isSuccess(), "success 필드는 false여야 합니다.");
        assertNotNull(response.getError(), "error 필드는 null이 아니어야 합니다.");
    }

    @RestController
    static class TestController {
        public static Map<String, String> body = Map.of("key", "body");

        @GetMapping("/test/valid-body")
        public Object testValidBody() {
            System.out.println("Endpoint /test/valid-body was called!");
            return body;// 유효한 body 반환
        }

        @GetMapping("/test/exception")
        public Object testException() {
            throw CustomError.of(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }


}