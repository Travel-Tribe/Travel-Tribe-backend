package com.zerobase.service;

import com.zerobase.entity.DepositEntity;
import com.zerobase.model.DepositDto;
import com.zerobase.model.exception.CustomException;
import com.zerobase.repository.DepositRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class DepositService {

    private final DepositRepository depositRepository;



    // depositId를 생성하기 위해서
    public DepositEntity createDepositOrder(Long postId, Long participationId,
        String userId) {
        log.info("DepositRepository initial save start");

        return DepositEntity.builder()
            .postId(postId)
            .participationId(participationId)
            .userId(userId)
            .build();
    }


    public DepositDto findByParticipationId(Long participationId) {
        return DepositDto.fromEntity(depositRepository
            .findByParticipationId(participationId).orElseThrow(
            CustomException::new));
    }

    public void save(DepositEntity depositEntity) {
        depositRepository.save(depositEntity);
    }
}
