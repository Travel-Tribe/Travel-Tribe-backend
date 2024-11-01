package com.zerobase.communities.type;

import com.zerobase.communities.entity.CommunityFileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityFileDto {
    private long communityId;
    private String fileName;

    public static CommunityFileDto fromEntity(
        CommunityFileEntity communityFileEntity) {
        return CommunityFileDto.builder()
            .communityId(communityFileEntity.getCommunityEntity().getCommunityId())
            .fileName(communityFileEntity.getFileName())
            .build();

    }
}
