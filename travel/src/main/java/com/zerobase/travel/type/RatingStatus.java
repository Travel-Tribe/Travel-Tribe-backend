package com.zerobase.travel.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RatingStatus {
    RATED("평가완료"),
    NOT_RATED("평가미완료");

    private final String korean;

    }