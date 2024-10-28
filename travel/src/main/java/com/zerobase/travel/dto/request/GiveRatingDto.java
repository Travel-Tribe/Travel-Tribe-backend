package com.zerobase.travel.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GiveRatingDto {

    private long receiverId;
    private double score;
    private String comment;

}
