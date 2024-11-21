package com.zerobase.travel.communities.type;

import com.zerobase.travel.communities.entity.CommunityEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommunityDto {

    Long communityId;
    String userId;
    String title;
    String content;

    public static CommunityDto fromEntity(CommunityEntity entity) {
        return CommunityDto.builder().
            communityId(entity.getCommunityId())
            .userId(entity.getUserId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .build();
    }
}
