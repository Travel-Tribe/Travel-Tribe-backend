package com.zerobase.travel.controller;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.dto.request.ReviewRequestDto;
import com.zerobase.travel.dto.response.ReviewResponseDto;
import com.zerobase.travel.repository.specification.ReviewSearchDto;
import com.zerobase.travel.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/posts/{postId}/reviews/{reviewId}")
    public ResponseEntity<ResponseMessage<ReviewResponseDto.Review>> getReview(
        @PathVariable long postId,
        @PathVariable long reviewId

    ) {
        return ResponseEntity.ok(ResponseMessage.success(
            reviewService.getReview(postId, reviewId)
        ));
    }

    @GetMapping("/reviews")
    public ResponseEntity<ResponseMessage<ReviewResponseDto.ReviewPage>> getReview(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "8") int size,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String content,
        @RequestParam(required = false) String continent,
        @RequestParam(required = false) String country,
        @RequestParam(required = false) Long userId
    ) {

        PageRequest pageRequest = PageRequest.of(page, size);
        ReviewSearchDto reviewSearchDto = ReviewSearchDto.builder()
            .title(title)
            .content(content)
            .continent(continent)
            .country(country)
            .userId(userId)
            .build();

        return ResponseEntity.ok(ResponseMessage.success(
            reviewService.getReviews(reviewSearchDto, pageRequest)
        ));
    }

}
