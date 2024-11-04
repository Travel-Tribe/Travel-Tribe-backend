package com.zerobase.travel.post.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.post.dto.request.PostDTO;
import com.zerobase.travel.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(
        @Valid @RequestBody PostDTO postDTO,
        @RequestHeader("X-User-Email") String userEmail) { // 이메일 추출
        postService.createPost(postDTO, userEmail);
        return ResponseEntity.status(CREATED).body(ResponseMessage.success());
    }

}

