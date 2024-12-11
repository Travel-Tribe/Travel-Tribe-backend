package com.zerobase.travel.communities.type;

import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.type.MBTI;
import java.time.LocalDate;
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
    private String nickname;
    private MBTI mbti;
    private String profileFile;
    private String title;
    private String content;
    private List<CommunityFileDto> files;
    private LocalDate createdAt;

    public static ResponseCommunityDto fromEntity(
        CommunityDto communityDto,List<CommunityFileDto> communityFileDtos,
        UserInfoResponseDTO userinfoDto){
        return ResponseCommunityDto.builder()
            .communityId(communityDto.getCommunityId())
            .userId(communityDto.getUserId())
            .nickname(userinfoDto.getNickname())
            .mbti(userinfoDto.getMbti())
            .profileFile(userinfoDto.getFileAddress())
            .title(communityDto.getTitle())
            .content(communityDto.getContent())
            .files(communityFileDtos)
            .createdAt(communityDto.getCreatedAt())
            .build();
    }

    public static ResponseCommunityDto fromEntity(
        CommunityDto communityDto,List<CommunityFileDto> communityFileDtos){
        return ResponseCommunityDto.builder()
            .communityId(communityDto.getCommunityId())
            .userId(communityDto.getUserId())
            .title(communityDto.getTitle())
            .content(communityDto.getContent())
            .files(communityFileDtos)
            .createdAt(communityDto.getCreatedAt())
            .build();
    }

}



