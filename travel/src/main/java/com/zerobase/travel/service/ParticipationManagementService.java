package com.zerobase.travel.service;

import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/*

deposit에 대한 처리와 participation 데이터를 관리하는 service
금융 데이터 처리 안정성을 위해서 복수인원 batch처리 없이 한명씩 transaction 처리

 Participation Status를 변화시키는 모든 비즈니스 케이스 반영
1. 여행을 참가
2. 여행을 투표를 통해서 전체 취소
3. 여행을 자진, 신고를 통해서 개인 취소
4. 여행을 전체 완료 (평점안매김)
5. 여행을 왼료한 후에 평점을 매김
6. 여행을 완료한 후에  보증금이 반환됨


 */


@Service
@Slf4j
@RequiredArgsConstructor
public class ParticipationManagementService {


    private final ParticipationService participationService;

    // 1. 여행을 참가
    // todo : TRANSACTION 처리기간이 길어서 phantom read 같은 문제 발생 가능성에 대해서 고민할 것, snapshot과 데이터 왜곡?
    @Transactional
    public ParticipationDto createParticipation(Long postId, String userId) {
        participationService.createParticipation(postId, userId);

        //보증금 지불준비 API호출

        //보증금 지급완료 API유저신호 수신

        // 전체 트랙잭션 처리

        return null;
    }


    //2. 여행을 투표를 통해서 취소, 투표완료시 해당 메소드 호출
    @Transactional
    public void unjoinWithDepositReturnedParticipation(Long postId,
        String userId) {

        // postId, userId를 통해서 participationEntity호출
        ParticipationEntity participationEntity= participationService
            .getParticipationEntityStatusOfJoin(postId,userId);

        // 배당금 반환 API 호출
        try{

        }catch (Exception e ){

        }

        // deposit 반환시점 기록
        participationService.setDateToReturnDeposit(participationEntity,
            LocalDateTime.now());

        // 상태를 변환하고 저장
        participationService.changeStatusOfParticipation(
            participationEntity, List.of(ParticipationStatus.JOIN),
            List.of(ParticipationStatus.JOIN_CANCEL,
                DepositStatus.DEPOSIT_RETURNED));

    }



    // 3. 여행을 자진, 신고를 통해서 개인 취소하여 배당금 몰수
    @Transactional
    public void unjoinWithDepositTakenParticipation(Long postId,
        String userId) {

        // postId, userId를 통해서 participationEntity호출
        ParticipationEntity participationEntity= participationService
            .getParticipationEntityStatusOfJoin(postId,userId);



        // 배당금 몰수 API 호출
        try{

        }catch (Exception e ){

        }

        // 상태를 변환하고 저장
        participationService.changeStatusOfParticipation(
            participationEntity, List.of(ParticipationStatus.JOIN),
            List.of(ParticipationStatus.JOIN_CANCEL,
                DepositStatus.DEPOSIT_TAKEN));
    }

    // 4. 인원이 여행을 완료하는 경우 ;
    public void travelFinishedParticipation(Long postId, String userId) {

        // 매일 게시글의 여행완료 시점을 확인

        // 참여의 상태를 변경 ; 여행완료

        // 보증금 반환일자를 확정


    }


    // 5. 여행을 왼료한 후에 평점을 매김
    public void giveRatingParticipation(Long postId, String userId) {

        // 참여의 상태를 변경 ; 평점매김

        // 보즈금 반환일자를 확정


    }

    // 6. 여행을 왼료한 후에 보증금이 반환됨
    public void returnDepositAfterTravelParticipation(Long postId,
        String userId) {

        // 매일 보증금 반환시점을 확인하고 보증금 반환을 확인하는 logic 추가


        // 배당금 반환 API 호출
        try{

        }catch (Exception e ){

        }

    }


}