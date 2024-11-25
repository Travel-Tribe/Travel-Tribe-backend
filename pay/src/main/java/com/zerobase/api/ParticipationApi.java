package com.zerobase.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipationApi {

    private final ParticipationClient participationClient;

    public ResponseEntity<Void> confirmParticipation(long participationId, String userId) {
       return participationClient.confirmParticipation(participationId, userId);
    }

    public ResponseEntity<Void> failedParticipation(long postId, String userId) {
        return participationClient.failedParticipation(postId, userId);
    }

    public ResponseEntity<Boolean> validateParticipationInfo(long postId,
        long participationId, String userId) {
        return participationClient.validateParticipationInfo(postId,participationId,userId);
    }

    }
