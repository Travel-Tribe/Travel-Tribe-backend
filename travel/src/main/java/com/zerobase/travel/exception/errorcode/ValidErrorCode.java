package com.zerobase.travel.exception.errorcode;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@RequiredArgsConstructor
public enum ValidErrorCode implements ErrorCode {

    Sample_ERROR("E99999", "샘플 에러 메시지");

    private final String errorCode;
    private final String errorMessage;

    public static ErrorCode findErrorCode(FieldError fieldError) {

        return Arrays.stream(ValidErrorCode.values())
            .filter(v -> v.getErrorCode().equals(fieldError.getDefaultMessage())) // E01002
            .findAny()
            .orElseThrow(() -> new RuntimeException());
    }

}
