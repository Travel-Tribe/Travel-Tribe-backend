package com.zerobase.user.dto.response;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    USER_NOT_FOUND_ERROR("E01005", "사용자가 없습니다."),
    USERNAME_ERROR("E01006","사용자 이름은 필수 입니다."),
    NICKNAME_ERROR("E01007","사용자 닉네임은 필수 입니다."),
    PHONE_ERROR("E01008","사용자 핸드폰번호는 필수 입니다"),
    PASSWORD_ERROR("E01009","비밀번호는 4자 이상 입력해야 합니다."),
    MAIL_ERROR("E01010","이메일 형식이 잘못되었습니다."),
    LOGIN_FAIL_ERROR("E01018","로그인 실패!");

    private final String errorCode;
    private final String errorMessage;

    public static ErrorCode findErrorCode(FieldError fieldError) {

        return Arrays.stream(UserErrorCode.values())
            .filter(v -> v.getErrorCode().equals(fieldError.getDefaultMessage())) // E01002
            .findAny()
            .get();
    }
}
