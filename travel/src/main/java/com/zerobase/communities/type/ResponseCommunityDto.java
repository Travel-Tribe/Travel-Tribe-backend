package com.zerobase.communities.type;


import java.util.List;

public class ResponseCommunityDto {
    int communityId;
    int userId;
    String continent;
    String region;
    String title;
    String content;
    List<CommunityFileDto> files;
}
