package com.zerobase.user.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProfileErrorCode implements ErrorCode {
    PROFILE_NOT_FOUND_ERROR("E01011", "프로필이 없습니다.");

    private final String errorCode;
    private final String errorMessage;
}
