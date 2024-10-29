package com.zerobase.travel.controller;

import com.zerobase.travel.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VoteController {

    private final VoteService voteService;

    //TODO 김용민 Response 공통 생기면 반영하기
    @PostMapping("/posts/{postId}/voting-starts")
    public void createVote(
        @PathVariable long postId
    ) {

        //TODO 김용민 추후 스프링 시큐리티 개발시 authentic에서 가져오기
        long userId = 1L;
        voteService.createVote(userId, postId);
    }

}
