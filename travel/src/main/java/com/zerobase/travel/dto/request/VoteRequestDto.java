package com.zerobase.travel.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class VoteRequestDto {

    @Getter
    @Setter
    @ToString
    public static class VoteVoting {
        private Boolean approval;
    }
}
