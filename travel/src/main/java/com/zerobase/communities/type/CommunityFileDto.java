package com.zerobase.communities.type;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommunityFileDto {
    private long communityId;
    private String fileName;
}
