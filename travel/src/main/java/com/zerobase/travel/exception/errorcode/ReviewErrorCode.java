package com.zerobase.travel.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorCode {

    ALREADY_WRITE_REVIEW("ERROR-RATING-00001", "후기를 이미 작성하였습니다.");

    private final String errorCode;
    private final String errorMessage;

}
