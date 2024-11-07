package com.zerobase.travel.api;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class UserApiRequestDto {

    @Getter
    @Setter
    @Builder
    @ToString
    @RequiredArgsConstructor
    public static class UpdateUserRating {
        private final Double avgRating;
    }


}
