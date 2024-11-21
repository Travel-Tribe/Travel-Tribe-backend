package com.zerobase.travel.communities.type;

import com.zerobase.travel.communities.entity.CommunityEntity;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommunityDto {

    private Long communityId;
    private String userId;
    private String title;
    private String content;
    private LocalDate createdAt;


    public static CommunityDto fromEntity(CommunityEntity entity) {
        return CommunityDto.builder().
            communityId(entity.getCommunityId())
            .userId(entity.getUserId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .createdAt(entity.getCreatedAt().toLocalDate())
            .build();
    }
}
