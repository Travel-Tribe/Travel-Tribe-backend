package com.zerobase.travel.post.dto.request;

import com.zerobase.travel.post.type.MBTI;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import lombok.Data;

@Data
public class PostSearchCriteria {
    private String title;
    private String content;
    private Continent continent;
    private Country country;
    private MBTI mbti;
}
