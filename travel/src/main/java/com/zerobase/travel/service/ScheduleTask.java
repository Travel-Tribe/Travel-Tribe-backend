package com.zerobase.travel.service;

import com.zerobase.travel.entity.ParticipationEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduleTask {

    private final ParticipationManagementService participationManagementService;
    private final ParticipationService participationService;


    @Scheduled(cron = "0 0 1 * * *")
    public void performParticipationTravelFinish() {
        participationManagementService.travelFinishedParticipations();
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
