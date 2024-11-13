package com.zerobase.user.dto.response;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@RequiredArgsConstructor
public enum ValidErrorCode implements ErrorCode {

    MIN_VALID_ERROR("USER-ERROR-VALID-00001", "값이 최솟값 이상이여야합니다."),
    VALID_ERROR("USER-ERROR-VALID-00002", "유효성 검사 실패."),
    DATABASE_VALID_ERROR("USER-ERROR-VALID-00003", "데이터베이스 제약 조건 위반."),
    FORBIDDEN("USER-ERROR-VALID-00004", "권한이 없습니다."),

    USER_NOT_FOUND_ERROR("USER-ERROR-VALID-00005", "사용자가 없습니다."),
    USERNAME_ERROR("USER-ERROR-VALID-00006", "사용자 이름은 필수 입니다."),
    NICKNAME_ERROR("USER-ERROR-VALID-00007", "사용자 닉네임은 필수 입니다."),
    PHONE_ERROR("USER-ERROR-VALID-00008", "사용자 핸드폰번호는 필수 입니다"),
    PASSWORD_ERROR("USER-ERROR-VALID-00009", "비밀번호는 4자 이상 입력해야 합니다."),
    NEW_PASSWORD_ERROR("USER-ERROR-VALID-00010", "새비밀번호는 4자 이상 입력해야 합니다."),
    MAIL_ERROR("USER-ERROR-VALID-00011", "이메일 형식이 잘못되었습니다."),
    LOGIN_FAIL_ERROR("USER-ERROR-VALID-00012", "로그인 실패!"),
    USER_PW_MISMATCH_ERROR("USER-ERROR-VALID-00013", "사용자 비밀번호 불일치!"),

    PROFILE_NOT_FOUND_ERROR("USER-ERROR-VALID-00014", "프로필이 없습니다."),
    MISSING_MBTI_ERROR("USER-ERROR-VALID-00015", "MBTI는 필수 입력 항목입니다."),
    MISSING_SMOKING_STATUS_ERROR("USER-ERROR-VALID-00016", "흡연 여부는 필수 입력 항목입니다."),
    MISSING_GENDER_ERROR("USER-ERROR-VALID-00017", "성별은 필수 입력 항목입니다."),
    MISSING_BIRTH_DATE_ERROR("USER-ERROR-VALID-00018", "생년월일은 필수 입력 항목입니다."),
    AUTHENTICATION_CODE_ERROR("USER-ERROR-VALID-00019", "인증 코드는 필수입니다.");

    private final String errorCode;
    private final String errorMessage;

    public static ErrorCode findErrorCode(FieldError fieldError) {

        return Arrays.stream(ValidErrorCode.values())
            .filter(v -> v.getErrorCode().equals(fieldError.getDefaultMessage())) // USER-ERROR-VALID-00002
            .findAny()
            .get();
    }
}
