package com.zerobase.travel.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BasicErrorCode implements ErrorCode {
    INVALID_REFRESH_TOKEN_ERROR("TRAVEL-ERROR-BASIC-00001", "유효하지 않은 Refresh 토큰입니다."),
    REFRESH_TOKEN_NOT_FOUND_ERROR("TRAVEL-ERROR-BASIC-00002", "존재하지 않는 Refresh 토큰입니다."),
    TOKEN_VALIDATION_ERROR("TRAVEL-ERROR-BASIC-00003", "토큰 검증 중 오류가 발생했습니다."),
    INTERNAL_SERVER_ERROR("TRAVEL-ERROR-BASIC-00004", "서버 오류 발생."),
    ILLEGAL_ARGUMENT__ERROR("TRAVEL-ERROR-BASIC-00005", "입력 값이 올바르지 않습니다."),
    POST_NOT_FOUND_ERROR("TRAVEL-ERROR-BASIC-00006", "게시글을 찾을 수 없습니다."),
    USER_NOT_FOUND_ERROR("TRAVEL-ERROR-BASIC-00007", "사용자를 찾을 수 없습니다."),
    USER_INFO_CALL_ERROR("TRAVEL-ERROR-BASIC-00008", "사용자 정보를 호출하는 데 실패했습니다."),
    PERMISSION_DENIED_ERROR("TRAVEL-ERROR-BASIC-00009", "권한이 없습니다."),
    INVALID_COUNTRY_VALUE("TRAVEL-ERROR-BASIC-00010", "국가명이 부적절합니다."),
    INVALID_CONTINENT_VALUE("TRAVEL-ERROR-BASIC-00011", "대륙명이 부적절합니다."),
    INVALID_MBTI_VALUE("TRAVEL-ERROR-BASIC-00012", "MBTI가 부적절합니다."),
    REFRESH_TOKEN_ERROR("TRAVEL-ERROR-BASIC-00013", "Refresh 토큰이 없습니다."),
    EXPIRED_TOKEN_ERROR("TRAVEL-ERROR-BASIC-00014", "만료된 토큰입니다."),
    CREATE_POST_ERROR("TRAVEL-ERROR-BASIC-00015", "모집 게시글 등록중 오류발생.");

    private final String errorCode;
    private final String errorMessage;
}
