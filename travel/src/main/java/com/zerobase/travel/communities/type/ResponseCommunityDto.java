package com.zerobase.travel.communities.type;

import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCommunityDto {

    private long communityId;
    private String userId;
    private Continent continent;
    private Country country;
    private String region;
    private String title;
    private String content;
    private List<CommunityFileDto> files;

    public static ResponseCommunityDto fromEntity(
        CommunityDto communityDto,List<CommunityFileDto> communityFileDtos){
        return ResponseCommunityDto.builder()
            .communityId(communityDto.getCommunityId())
            .userId(communityDto.getUserId())
            .continent(communityDto.getContinent())
            .country(communityDto.getCountry())
            .region(communityDto.getRegion())
            .title(communityDto.getTitle())
            .content(communityDto.getContent())
            .files(communityFileDtos)
            .build();
    }

}



