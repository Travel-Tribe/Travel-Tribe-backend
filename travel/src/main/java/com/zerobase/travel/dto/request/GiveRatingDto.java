package com.zerobase.travel.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class GiveRatingDto {

    private long receiverId;
    private double score;
    private String comment;

}
