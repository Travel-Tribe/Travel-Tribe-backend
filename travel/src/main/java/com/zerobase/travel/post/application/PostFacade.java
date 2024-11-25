package com.zerobase.travel.post.application;

import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.post.dto.request.PostDTO;
import com.zerobase.travel.post.dto.response.createdPostResponseDTO;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.service.PostService;
import com.zerobase.travel.service.ParticipationManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostFacade {

    private final PostService postService;
    private final ParticipationManagementService participationManagementService;

    @Transactional
    public createdPostResponseDTO createPost(PostDTO postDTO, String userEmail) {

        PostEntity post = postService.createPost(postDTO, userEmail);

        ParticipationDto participationDto = participationManagementService.readyParticipation(
            post.getPostId(), String.valueOf(post.getUserId()), userEmail);

        return createdPostResponseDTO.builder()
            .postId(post.getPostId())
            .participationId(participationDto.getParticipationId())
            .build();
    }
}
