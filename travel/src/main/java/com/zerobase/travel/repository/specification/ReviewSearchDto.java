package com.zerobase.travel.repository.specification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ReviewSearchDto {

    String title;
    String content;
    String continent;
    String country;

}
