package com.zerobase.communities.type;

import com.zerobase.typeCommon.Continent;
import com.zerobase.typeCommon.Country;
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

    long communityId;
    long userId;
    Continent continent;
    Country country;
    String region;
    String title;
    String content;
    List<CommunityFileDto> files;

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



