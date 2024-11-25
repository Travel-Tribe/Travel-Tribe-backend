package com.zerobase.travel.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ParticipationStatus {
    JOIN_READY("참가준비"),
    JOIN_FAILED("참가실패"),
    JOIN("참가"),
    JOIN_CANCEL("참가취소"),
    TRAVEL_FINISHED("여행완료");
    private final String korean;

    }