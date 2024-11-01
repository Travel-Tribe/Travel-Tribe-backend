package com.zerobase.travel.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BasicErrorCode implements ErrorCode {
    REFRESH_TOKEN_ERROR("E01012", "Refresh 토큰이 없습니다."),
    EXPIRED_TOKEN_ERROR("E01013", "만료된 토큰입니다."),
    INVALID_REFRESH_TOKEN_ERROR("E01014", "유효하지 않은 Refresh 토큰입니다."),
    REFRESH_TOKEN_NOT_FOUND_ERROR("E01015", "존재하지 않는 Refresh 토큰입니다."),
    TOKEN_VALIDATION_ERROR("E01016", "토큰 검증 중 오류가 발생했습니다."),
    INTERNAL_SERVER_ERROR("E01017", "서버 오류 발생."),
    ILLEGAL_ARGUMENT__ERROR("E01018", "입력 값이 올바르지 않습니다.");

    private final String errorCode;
    private final String errorMessage;
}
