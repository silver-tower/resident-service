package com.silvertown.resident_service.common.response;

import com.silvertown.resident_service.common.exception.CustomError;
import com.silvertown.resident_service.common.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ApiResponse")
class ApiResponseTest {

    @Test
    @DisplayName("성공 응답 생성 및 필드 값 확인 테스트")
     void shouldCreateSuccessResponseAndMatchFields() {
        String testData = "Test Data";

        ApiResponse<String> response = ApiResponse.success(testData);

        assertNotNull(response, "response 값 null");
        assertTrue(response.isSuccess(), "success 값 불일치 {} : {}" + true + response.isSuccess());
        assertEquals(testData, response.getData(), "data 값 불일치 {} : {}" + testData + response.getData());
        assertNull(response.getError(), "error 값이 null이 아님 {}" + response.getError());
        assertNotNull(response.getTimestamp(), "timestamp 값 null");
    }

    @Test
    @DisplayName("에러 응답 생성 - ErrorCode 사용")
    void shouldCreateErrorResponseUsingErrorCode() {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST;

        ApiResponse<Object> response = ApiResponse.error(errorCode);

        assertNotNull(response, "response 값 null");
        assertFalse(response.isSuccess(), "success 값 불일치 {} : {}" + false + response.isSuccess());
        assertNull(response.getData(), "data 값이 null이 아님 {}" + response.getData());
        assertNotNull(response.getError(), "error 값 null");
        assertEquals(errorCode.getCode(), response.getError().getCode(), "errorCode 값 불일치 {} : {}" + errorCode + response.getError().getCode());
        assertNotNull(response.getTimestamp(), "timestamp 값 null");
    }

    @Test
    @DisplayName("에러 응답 생성 - CustomError 사용")
    void shouldCreateErrorResponseUsingCustomError() {
        CustomError customError = CustomError.of(ErrorCode.BAD_REQUEST);

        ApiResponse<Object> response = ApiResponse.error(customError);

        assertNotNull(response, "response 값 null");
        assertFalse(response.isSuccess(), "success 값 불일치 {} : {}" + false + response.isSuccess());
        assertNull(response.getData(), "data 값이 null이 아님 {}" + response.getData());
        assertEquals(customError, response.getError(), "error 값 불일치 {} : {}" + customError + response.getError());
        assertNotNull(response.getTimestamp(), "timestamp 값 null");
    }

    @Test
    @DisplayName("에러 응답 생성 시 ErrorCode가 null인 경우 오류 발생")
    void shouldCreateErrorResponseWithNullErrorCode() {
        assertThrows(Exception.class, () -> {
            ApiResponse.error((ErrorCode) null);
        });
    }

    @Test
    @DisplayName("에러 응답 생성 시 CustomError가 null인 경우 테스트")
    void shouldCreateErrorResponseWithNullCustomError() {
        ApiResponse<Object> response = ApiResponse.error((CustomError) null);

        assertNotNull(response, "response 값 null");
        assertFalse(response.isSuccess(), "success 값 불일치 {} : {}" + false + response.isSuccess());
        assertNull(response.getData(), "data 값이 null이 아님 {}" + response.getData());
        assertNull(response.getError(), "error 값이 null이 아님 {}" + response.getError());
        assertNotNull(response.getTimestamp(), "timestamp 값 null");
    }
}