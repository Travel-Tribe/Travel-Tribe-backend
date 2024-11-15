package com.zerobase.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TravelApi {

    private final TravelClient travelClient;

    public ResponseEntity<Void> confirmParticipation(long participationId, String userId) {
       return travelClient.confirmParticipation(participationId, userId);
    }

    public ResponseEntity<Void> failedParticipation(long postId, String userId) {
        return travelClient.failedParticipation(postId, userId);
    }

    public ResponseEntity<Boolean> validateParticipationInfo(long postId,
        long participationId, String userId) {
        return travelClient.validateParticipationInfo(postId,participationId,userId);
    }

    }
