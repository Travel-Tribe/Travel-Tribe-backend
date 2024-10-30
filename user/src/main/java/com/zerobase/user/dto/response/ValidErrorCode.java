package com.zerobase.user.dto.response;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@RequiredArgsConstructor
public enum ValidErrorCode implements ErrorCode {

    MIN_VALID_ERROR("E01001", "값이 최솟값 이상이여야합니다."),
    VALID_ERROR("E01002", "유효성 검사 실패."),
    DATABASE_VALID_ERROR("E01003", "데이터베이스 제약 조건 위반."),
    FORBIDDEN("E01004", "권한이 없습니다.");

    private final String errorCode;
    private final String errorMessage;
}
