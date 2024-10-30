package com.zerobase.user.dto.response;

import java.util.Arrays;
import org.springframework.validation.FieldError;

public interface ErrorCode {

    String getErrorCode();
    String getErrorMessage();

    // 인터페이스에는 메서드 선언만 포함
    static ErrorCode findErrorCode(FieldError fieldError) {
        return null; // 인터페이스에는 선언만 추가하므로 기본 구현을 제공하지 않습니다.
    }
}
