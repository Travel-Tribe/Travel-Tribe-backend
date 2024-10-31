package com.zerobase.user.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BasicErrorCode implements ErrorCode {
    REFRESH_TOKEN_NOT_FOUND_IN_COOKIE_ERROR("E01014", "Refresh 토큰이 쿠키에 없습니다."),
    EXPIRED_TOKEN_ERROR("E01015", "만료된 토큰입니다."),
    INVALID_REFRESH_TOKEN_CATEGORY_ERROR("E01016", " 토큰 카테고리가 Refresh가 아닙니다."),
    REFRESH_TOKEN_NOT_IN_DATABASE("E01017", "Refresh 토큰이 DB에 존재하지 않습니다."),
    REFRESH_TOKEN_NOT_FOUND_ERROR("E01018", "존재하지 않는 Refresh 토큰입니다."),
    INVALID_TOKEN_FORMAT_ERROR("E01019", "적합하지 않는 JWT 토큰 형식입니다."),
    TOKEN_VALIDATION_ERROR("E01020", "토큰 검증 중 오류가 발생했습니다."),
    INTERNAL_SERVER_ERROR("E01021", "서버 오류 발생."),
    ILLEGAL_ARGUMENT__ERROR("E01022", "입력 값이 올바르지 않습니다."),
    UNAUTHORIZED_ERROR("E01023", "인증 정보가 없습니다."),
    NOT_ACCESS_TOKEN_ERROR("E01024", "access 토큰이 아닙니다.");

    private final String errorCode;
    private final String errorMessage;
}
