package com.zerobase.user.dto.response;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@RequiredArgsConstructor
public enum ProfileErrorCode implements ErrorCode {
    PROFILE_NOT_FOUND_ERROR("E01030", "프로필이 없습니다."),
    MISSING_MBTI_ERROR("E01031", "MBTI는 필수 입력 항목입니다."),
    MISSING_SMOKING_STATUS_ERROR("E01032", "흡연 여부는 필수 입력 항목입니다."),
    MISSING_GENDER_ERROR("E01033", "성별은 필수 입력 항목입니다."),
    MISSING_BIRTH_DATE_ERROR("E01034", "생년월일은 필수 입력 항목입니다.");

    private final String errorCode;
    private final String errorMessage;

    public static ErrorCode findErrorCode(FieldError fieldError) {

        return Arrays.stream(ProfileErrorCode.values())
            .filter(v -> v.getErrorCode().equals(fieldError.getDefaultMessage())) // E01002
            .findAny()
            .get();
    }
}
