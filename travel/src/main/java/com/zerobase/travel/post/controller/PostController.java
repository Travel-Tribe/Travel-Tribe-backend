package com.zerobase.travel.post.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.post.dto.request.PostDTO;
import com.zerobase.travel.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(
        @Valid @RequestBody PostDTO postDTO,
        @RequestHeader("X-User-Email") String userEmail) { // 이메일 추출
        log.info("email: " + userEmail);
        postService.createPost(postDTO, userEmail);
        return ResponseEntity.status(CREATED).body(ResponseMessage.success());
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @Valid @RequestBody PostDTO postDTO,
        @RequestHeader("X-User-Email") String userEmail){
        log.info("email: " + userEmail);
        postService.updatePost(postId, postDTO, userEmail);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId, @RequestHeader("X-User-Email") String userEmail){
        log.info("email: " + userEmail);
        postService.deletePost(postId, userEmail);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

}

