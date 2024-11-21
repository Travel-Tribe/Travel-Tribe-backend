package com.zerobase.travel.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ParticipationErrorCode implements ErrorCode {

    POST_NOT_EXISTING("ERROR-PARTICIPATION-00001", "존재하지 않는 POST ID입니다."),
    USER_PARTICIPATION_LIMIT("ERROR-PARTICIPATION-00002", "User가 참여할 수 있는 여행을 초과하였습니다"),
    PARTICIPATION_ALREADY_MADE("ERROR-PARTICIPATION-00003", "이미 참여한 여행입니다."),
    PARTICIPATION_JOINREADY_ALREADYEXISTING("ERROR-PARTICIPATION-00004", "결제 대기중인 참여가 이미 존재합니다"),
    POST_PARTICIPATION_LIMIT("ERROR-PARTICIPATION-00005", "모집글이 수용할 수 있는 참가인원을 초과하였습니다."),
    APPLICANT_POST_LIMIT_MATCHED("ERROR-PARTICIPATION-00006", "모집글의 제한사항과 유저의 프로필이 일치합니다"),
    PARTICIPATION_STATUS_ERROR("ERROR-PARTICIPATION-00007", "참여자의 참여상태가 예상과 다릅니다"),
    PARTICIPATION_NOT_EXIST("ERROR-PARTICIPATION-00008", "찾고자하는 참여가 존재하지 않습니다");


    private final String errorCode;
    private final String errorMessage;




}
