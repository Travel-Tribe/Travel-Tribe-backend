package com.zerobase.user.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BasicErrorCode implements ErrorCode {
    REFRESH_TOKEN_NOT_FOUND_IN_COOKIE_ERROR("USER-ERROR-BASIC-00001", "Refresh 토큰이 쿠키에 없습니다."),
    EXPIRED_TOKEN_ERROR("USER-ERROR-BASIC-00002", "만료된 토큰입니다."),
    INVALID_REFRESH_TOKEN_CATEGORY_ERROR("USER-ERROR-BASIC-00003", " 토큰 카테고리가 Refresh가 아닙니다."),
    REFRESH_TOKEN_NOT_IN_DATABASE("USER-ERROR-BASIC-00004", "Refresh 토큰이 DB에 존재하지 않습니다."),
    REFRESH_TOKEN_NOT_FOUND_ERROR("USER-ERROR-BASIC-00005", "존재하지 않는 Refresh 토큰입니다."),
    INVALID_TOKEN_FORMAT_ERROR("USER-ERROR-BASIC-00006", "적합하지 않는 JWT 토큰 형식입니다."),
    TOKEN_VALIDATION_ERROR("USER-ERROR-BASIC-00007", "토큰 검증 중 오류가 발생했습니다."),
    INTERNAL_SERVER_ERROR("USER-ERROR-BASIC-00008", "서버 오류 발생."),
    ILLEGAL_ARGUMENT__ERROR("USER-ERROR-BASIC-00009", "입력 값이 올바르지 않습니다."),
    UNAUTHORIZED_ERROR("USER-ERROR-BASIC-00010", "인증 정보가 없습니다."),
    NOT_ACCESS_TOKEN_ERROR("USER-ERROR-BASIC-00011", "access 토큰이 아닙니다."),
    NOT_FOUND_EMAIL_AUTHENTICATION_ERROR("USER-ERROR-BASIC-00012", "이메일 인증 정보가 존재하지 않습니다."),
    AUTHENTICATION_CODE_EXPIRED_ERROR("USER-ERROR-BASIC-00013", "인증 코드가 만료되었습니다."),
    INVALID_AUTHENTICATION_CODE_ERROR("USER-ERROR-BASIC-00014", "인증 코드가 유효하지 않습니다."),
    ILLEGAL_EMAIL_ADDRESS_ERROR("USER-ERROR-BASIC-00015", "잘못된 이메일 주소"),
    EMAIL_SENDING_ERROR("USER-ERROR-BASIC-00016", "이메일 전송 실패"),
    CREATE_TOKEN_ERROR("USER-ERROR-BASIC-00017", "토큰 생성 중 오류가 발생했습니다."),
    LOCK_ACQUISITION_FAILED_ERROR("USER-ERROR-BASIC-00018", "레디스 락 획득 실패."),
    DEACTIVATED_USER_ERROR("USER-ERROR-BASIC-00019", "탈퇴한 회원 입니다."),
    SUSPENDED_USER_ERROR("USER-ERROR-BASIC-00020", "정지당한 회원 입니다.");

    private final String errorCode;
    private final String errorMessage;
}
