package com.zerobase.travel.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorCode {

    ALREADY_WRITE_REVIEW("ERROR-REVIEW-00001", "후기를 이미 작성하였습니다."),
    NOT_MY_REVIEW("ERROR-REVIEW-00002", "내가 작성한 후기가 아닙니다."),
    THIS_REVIEW_NOT_IN_POST("ERROR-REVIEW-00003", "모집 게시글에 대한 후기가 아닙니다."),
    NOT_FOUND_REVIEW("ERROR-REVIEW-00004", "후기를 찾을 수 없습니다.");

    private final String errorCode;
    private final String errorMessage;

}
