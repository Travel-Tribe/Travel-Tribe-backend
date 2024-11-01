package com.zerobase.travel.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ReportingRequestDto {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class ReportUser {

        private Long receiverUserId;

        private String comment;
    }

}
