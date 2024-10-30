package com.zerobase.travel.controller;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.dto.request.VoteRequestDto;
import com.zerobase.travel.dto.response.VoteResponseDto;
import com.zerobase.travel.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VoteController {

    private final VoteService voteService;
    
    @PostMapping("/posts/{postId}/voting-starts")
    public ResponseEntity<ResponseMessage<Void>> createVote(
        @PathVariable long postId
    ) {

        //TODO 김용민 추후 스프링 시큐리티 개발시 authentic에서 가져오기
        long userId = 1L;
        voteService.createVote(userId, postId);

        return ResponseEntity.ok(ResponseMessage.success());
    }

    @GetMapping("/posts/{postId}/voting-starts")
    public ResponseEntity<ResponseMessage<VoteResponseDto.VotingStart>> getVotingStart(
        @PathVariable long postId
    ) {

        //TODO 김용민 추후 스프링 시큐리티 개발시 authentic에서 가져오기
        long userId = 1L;

        return ResponseEntity.ok(ResponseMessage.success(
            voteService.getVotingStart(userId, postId)
        ));
    }

    @PostMapping("/posts/{postId}/voting-starts/{votingStartsId}/votings")
    public ResponseEntity<ResponseMessage<Void>> voteVoting(
        @PathVariable long postId,
        @PathVariable long votingStartsId,
        @RequestBody VoteRequestDto.VoteVoting request

    ) {

        //TODO 김용민 추후 스프링 시큐리티 개발시 authentic에서 가져오기
        long userId = 1L;
        voteService.voteVoting(userId, postId, votingStartsId, request.getApproval());

        return ResponseEntity.ok(ResponseMessage.success());
    }

    @GetMapping("/posts/{postId}/voting-starts/{votingStartsId}/votings")
    public ResponseEntity<ResponseMessage<VoteResponseDto.GetVote>> getVote(
        @PathVariable long postId,
        @PathVariable long votingStartsId
    ) {

        //TODO 김용민 추후 스프링 시큐리티 개발시 authentic에서 가져오기
        long userId = 1L;
        return ResponseEntity.ok(ResponseMessage.success(
            voteService.getVote(userId, postId, votingStartsId)
        ));
    }

}
