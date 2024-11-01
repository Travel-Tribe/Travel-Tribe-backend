package com.zerobase.communities.type;

import com.zerobase.communities.entity.CommunityEntity;
import com.zerobase.typeCommon.Continent;
import com.zerobase.typeCommon.Country;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommunityDto {

    long communityId;
    long userId;
    Continent continent;
    Country country;
    String region;
    String title;
    String content;

    public static CommunityDto fromEntity(CommunityEntity entity) {
        return CommunityDto.builder().
            communityId(entity.getCommunityId())
            .userId(entity.getUserId())
            .continent(entity.getContinent())
            .country(entity.getCountry())
            .region(entity.getRegion())
            .title(entity.getTitle())
            .content(entity.getContent())
            .build();
    }
}
