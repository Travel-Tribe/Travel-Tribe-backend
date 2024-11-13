package com.zerobase.travel.post.application;

import com.zerobase.travel.post.dto.request.PostDTO;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.service.PostService;
import com.zerobase.travel.service.ParticipationManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostService postService;
    private final ParticipationManagementService participationManagementService;

    public void createPost(PostDTO postDTO, String userEmail) {
        PostEntity post = postService.createPost(postDTO, userEmail);
        participationManagementService.readyParticipation(post.getPostId(), String.valueOf(post.getUserId()));
    }
}
