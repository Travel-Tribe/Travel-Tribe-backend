package com.zerobase.user.service;

import static com.zerobase.user.dto.response.BasicErrorCode.INTERNAL_SERVER_ERROR;

import com.zerobase.user.exception.BizException;
import com.zerobase.user.feignclient.TravelClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

@Service
@RequiredArgsConstructor
public class UserParticipationService {

    private final TravelClient travelClient;

    /**
     * 유저의 완료된 여행 수 조회
     *
     * @param userId 유저 ID
     * @return 완료된 여행 수
     */
    public Integer getCompletedTripsCount(String userId) {
        ResponseEntity<Integer> response = travelClient.getParticipationsCompletedByUserId(userId);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            // 예외 처리 로직 추가 (예: 커스텀 예외 던지기)
            throw new BizException(INTERNAL_SERVER_ERROR);
        }
    }
}
