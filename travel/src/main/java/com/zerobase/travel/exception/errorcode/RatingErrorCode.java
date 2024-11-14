package com.zerobase.travel.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RatingErrorCode implements ErrorCode {

    SCORE_OUT_OF_RANGE("ERROR-RATING-00001", "평점 범위를 벗어납니다."),
    SCORE_OUT_OF_UNIT("ERROR-RATING-00002", "평점 단위가 다릅니다."),
    ALREADY_RATING("ERROR-RATING-00003", "이미 평점을 주었습니다."),
    NOT_FOUND_USER_RATING("ERROR-RATING-00004", "사용자 평점을 찾을 수 없습니다."),
    NOT_YET_GIVE_RATING("ERROR-RATING-00005", "여행종료 후 평점을 줄 수 있습니다.");

    private final String errorCode;
    private final String errorMessage;

}
