package com.zerobase.travel.controller.view;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.dto.response.ReviewResponseDto.Review;
import com.zerobase.travel.dto.response.ReviewViewResponseDto;
import com.zerobase.travel.service.view.ReviewViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewViewController {

    public final ReviewViewService reviewViewService;

    @GetMapping("/posts/{postId}/reviews/{reviewId}/view")
    public ResponseEntity<ResponseMessage<ReviewViewResponseDto.GetReview>> getReview(
        @PathVariable long postId,
        @PathVariable long reviewId

    ) {
        return ResponseEntity.ok(ResponseMessage.success(
            reviewViewService.getReview(postId, reviewId)
        ));
    }

}
