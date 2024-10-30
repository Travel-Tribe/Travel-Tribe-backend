package com.zerobase.travel.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportingErrorCode implements ErrorCode {

    ALREADY_REPORTING("ERROR-REPORTING-00001", "이미 신고하였습니다."),
    SCORE_OUT_OF_UNIT("ERROR-REPORTING-00002", "평점 단위가 다릅니다.");

    private final String errorCode;
    private final String errorMessage;

}
