package com.zerobase.travel.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommunityErrorCode implements ErrorCode {

    COMMUNITY_NOT_EXIST("ERROR-COMMUNITY-00001", "존재하지 않는 COMMUNITY ID입니다."),
    COMMUNITY_DELETED("ERROR-COMMUNITY-00002", "이미 삭제된 COMMUNITY ID입니다."),
    USER_UNAUTHORIZED_REQUEST("ERROR-COMMUNITY-00003", "유저에게 권한이 없습니다.");



    private final String errorCode;
    private final String errorMessage;




}
