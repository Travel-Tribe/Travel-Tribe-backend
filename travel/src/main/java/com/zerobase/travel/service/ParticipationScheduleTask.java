package com.zerobase.travel.service;

import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.type.ParticipationStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ParticipationScheduleTask {

    private final ParticipationManagementService participationManagementService;
    private final ParticipationService participationService;


    @Scheduled(cron = "0 0 1 * * *")
    public void performParticipationTravelFinish() {
        participationManagementService.travelFinishedParticipations();
    }

    // 매시간 정각마다 JoinReady인 상태의 Participation이 24시간이 지나면 정리진행함.
    @Scheduled(cron = "0 0 * * * *")
    public void clearParticipationJoinReady() {
        List<ParticipationDto> participationDtos
            = participationService.getParticipationsByJoinReadyFor24Hours();

        participationDtos.stream()
            .forEach(e->participationManagementService.failedPaymentParticipation
                (e.getParticipationId(),e.getUserId()));
    }


    // 보증금을 반환할 대상을 호출하고, 보증금 반환로직을 실행한다.
    @Scheduled(cron = "0 0 1 * * *")
    public void performParticipationDepositReturn() {
            List<ParticipationEntity> participationEntities = participationService.getParticipationsByDepositReturnDate();
            for (ParticipationEntity participationEntity : participationEntities) {
                try {
                    participationManagementService.returnDepositAfterTravelFinished(
                        participationEntity);
                }catch (Exception exception){
                    log.error("Error processing deposit return for participation ID: {}", participationEntity.getParticipationId(), exception);
                }
            }

        }


    }
