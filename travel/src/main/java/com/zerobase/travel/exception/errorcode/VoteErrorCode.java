package com.zerobase.travel.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VoteErrorCode implements ErrorCode {

    ALREADY_VOTING_START("ERROR-VOTING-00001", "이미 투표가 시작되었습니다."),
    NOT_READY_VOTING_START("ERROR-VOTING-00002", "투표가 시작되지 않았습니다."),
    ALREADY_VOTE("ERROR-VOTING-00003", "이미 투표 하였습니다.");

    private final String errorCode;
    private final String errorMessage;
}
