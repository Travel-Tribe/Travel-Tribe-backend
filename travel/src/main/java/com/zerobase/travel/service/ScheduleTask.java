package com.zerobase.travel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleTask {

    private final ParticipationManagementService participationManagementService;


    @Scheduled(cron= "0 0 1 * * *")
    public void performParticipationTravelFinish(){
        participationManagementService.travelFinishedParticipations();
    }


}
