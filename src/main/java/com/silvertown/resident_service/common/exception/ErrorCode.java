package com.silvertown.resident_service.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    // 공통 에러
    BAD_REQUEST("C4000", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR("C5000", HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    UNAUTHORIZED("C4010", HttpStatus.UNAUTHORIZED, "인증되지 않은 요청입니다."),
    FORBIDDEN("C4030", HttpStatus.FORBIDDEN, "권한이 없습니다."),

    // 사용자 관련 에러
    USER_NOT_FOUND("U4004", HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    USER_DUPLICATE("U4002", HttpStatus.CONFLICT, "중복된 사용자입니다."),
    USER_INPUT_INVALID("U4001", HttpStatus.BAD_REQUEST, "유효하지 않은 사용자 입력입니다."),
    USER_PASSWORD_MISMATCH("U4005", HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    USER_ACCOUNT_LOCKED("U4006", HttpStatus.FORBIDDEN, "계정이 잠겼습니다."),
    USER_ACCOUNT_DISABLED("U4007", HttpStatus.FORBIDDEN, "계정이 비활성화 상태입니다."),
    USER_SESSION_EXPIRED("U4008", HttpStatus.UNAUTHORIZED, "세션이 만료되었습니다."),
    USER_EMAIL_NOT_VERIFIED("U4009", HttpStatus.UNAUTHORIZED, "이메일 인증이 필요합니다."),

    // 입주민 관련 에러
    RESIDENT_BAD_REQUEST("R4001", HttpStatus.BAD_REQUEST, "입력 데이터 오류입니다."),
    RESIDENT_NOT_FOUND("R4004", HttpStatus.NOT_FOUND, "요청된 입주민 또는 거주지가 존재하지 않습니다."),
    RESIDENT_STATUS_INVALID("R4005", HttpStatus.CONFLICT, "입주민 상태가 유효하지 않습니다."),

    // 거주지 관련 에러
    RESIDENCE_BAD_REQUEST("RE4001", HttpStatus.BAD_REQUEST, "입력 데이터 오류입니다."),
    RESIDENCE_NOT_FOUND("RE4002", HttpStatus.NOT_FOUND, "거주지 상태가 유효하지 않습니다."),
    RESIDENCE_DUPLICATE("RE4003", HttpStatus.CONFLICT, "중복된 거주지가 이미 존재합니다."),
    RESIDENCE_STATE_INVALID("RE4004", HttpStatus.CONFLICT, "거주지 상태가 유효하지 않습니다."),
    RESIDENCE_UNAVAILABLE("RE4005", HttpStatus.BAD_REQUEST, "해당 거주지는 현재 사용 불가 상태입니다."),

    // 비상 연락처 관련 에러
    EMERGENCY_CONTACT_BAD_REQUEST("E4001", HttpStatus.BAD_REQUEST, "비상 연락처 입력 데이터가 유효하지 않습니다."),
    EMERGENCY_CONTACT_NOT_FOUND("E4040", HttpStatus.NOT_FOUND, "비상 연락처를 찾을 수 없습니다."),
    EMERGENCY_CONTACT_DUPLICATE("E4002", HttpStatus.CONFLICT, "중복된 비상 연락처입니다."),
    EMERGENCY_CONTACT_SERVER_ERROR("E5000", HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류입니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
