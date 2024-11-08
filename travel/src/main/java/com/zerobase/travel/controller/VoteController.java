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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VoteController {

    private final VoteService voteService;
    
    @PostMapping("/posts/{postId}/voting-starts")
    public ResponseEntity<ResponseMessage<Void>> createVote(
        @RequestHeader("X-User-Id") long userId,
        @PathVariable long postId
    ) {
        voteService.createVote(userId, postId);
        return ResponseEntity.ok(ResponseMessage.success());
    }

    @GetMapping("/posts/{postId}/voting-starts")
    public ResponseEntity<ResponseMessage<VoteResponseDto.VotingStart>> getVotingStart(
        @RequestHeader("X-User-Id") long userId,
        @PathVariable long postId
    ) {
        return ResponseEntity.ok(ResponseMessage.success(
            voteService.getVotingStart(userId, postId)
        ));
    }

    @PostMapping("/posts/{postId}/voting-starts/{votingStartsId}/votings")
    public ResponseEntity<ResponseMessage<Void>> voteVoting(
        @RequestHeader("X-User-Id") long userId,
        @PathVariable long postId,
        @PathVariable long votingStartsId,
        @RequestBody VoteRequestDto.VoteVoting request

    ) {
        voteService.voteVoting(userId, postId, votingStartsId, request.getApproval());
        return ResponseEntity.ok(ResponseMessage.success());
    }

    @GetMapping("/posts/{postId}/voting-starts/{votingStartsId}/votings")
    public ResponseEntity<ResponseMessage<VoteResponseDto.GetVote>> getVote(
        @RequestHeader("X-User-Id") long userId,
        @PathVariable long postId,
        @PathVariable long votingStartsId
    ) {
        return ResponseEntity.ok(ResponseMessage.success(
            voteService.getVote(userId, postId, votingStartsId)
        ));
    }

}
