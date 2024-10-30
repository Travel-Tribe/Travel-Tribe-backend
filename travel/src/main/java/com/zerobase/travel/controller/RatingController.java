package com.zerobase.travel.controller;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.dto.request.GiveRatingDto;
import com.zerobase.travel.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RatingController {

    private final RatingService ratingService;

    //TODO 김용민 Response 공통 생기면 반영하기
    @PostMapping("/posts/{postId}/rating")
    public ResponseEntity<ResponseMessage<Void>> registerRating(
        @PathVariable long postId,
        @RequestBody GiveRatingDto giveRatingDto
    ) {

        //TODO 김용민 추후 스프링 시큐리티 개발시 authentic에서 가져오기
        long userId = 1L;

        ratingService.giveRating(giveRatingDto, postId, userId);

        return ResponseEntity.ok(ResponseMessage.success());
    }

}
