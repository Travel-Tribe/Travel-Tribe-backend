package com.zerobase.travel.controller;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.dto.request.ReviewRequestDto;
import com.zerobase.travel.service.ReviewService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/posts/{postId}/reviews")
    public ResponseEntity<ResponseMessage<Void>> createReview(
        @RequestHeader("X-User-Email") String userEmail,
        @PathVariable long postId,
        @RequestBody ReviewRequestDto.CreateReview request
    ) {

        reviewService.createReview(request, userEmail, postId);

        return ResponseEntity.ok(ResponseMessage.success());
    }

    @PutMapping("/posts/{postId}/reviews/{reviewId}")
    public ResponseEntity<ResponseMessage<Void>> modifyReview(
        @RequestHeader("X-User-Email") String userEmail,
        @PathVariable long postId,
        @PathVariable long reviewId,
        @RequestBody ReviewRequestDto.ModifyReview request

    ) {

        reviewService.modifyReview(request, userEmail, postId, reviewId);

        return ResponseEntity.ok(ResponseMessage.success());
    }

    @DeleteMapping("/posts/{postId}/reviews/{reviewId}")
    public ResponseEntity<ResponseMessage<Void>> deleteReview(
        @RequestHeader("X-User-Email") String userEmail,
        @PathVariable long postId,
        @PathVariable long reviewId

    ) {

        reviewService.deleteReview(userEmail, postId, reviewId);

        return ResponseEntity.ok(ResponseMessage.success());
    }

}
