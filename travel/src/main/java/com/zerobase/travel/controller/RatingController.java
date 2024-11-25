package com.zerobase.travel.controller;

import com.zerobase.travel.application.RatingFacade;
import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.dto.request.GiveRatingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RatingController {

    private final RatingFacade ratingFacade;
    
    @PostMapping("/posts/{postId}/rating")
    public ResponseEntity<ResponseMessage<Void>> registerRating(
        @RequestHeader("X-User-Id") long userId,
        @PathVariable long postId,
        @RequestBody GiveRatingDto giveRatingDto
    ) {

        ratingFacade.giveRating(giveRatingDto, postId, userId);
        return ResponseEntity.ok(ResponseMessage.success());
    }

}
