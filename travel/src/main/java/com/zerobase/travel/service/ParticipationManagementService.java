package com.zerobase.travel.service;

import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.type.RatingStatus;
import java.time.LocalDate;
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
5.1 평점을 여행완료 후 7일 이후에 매긴 경우
5.2 평점을 여행완료 후 7일 전에 매긴 경우


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

        ParticipationDto participation = participationService.createParticipation(
            postId, userId);

        //보증금 지불준비 API호출

        //보증금 지급완료 API유저신호 수신

        // 전체 트랙잭션 처리

        return participation;
    }


    //2. 여행을 투표를 통해서 취소, 투표완료시 해당 메소드 호출
    @Transactional
    public void unjoinWithDepositReturnedParticipation(Long postId,
        String userId) {

        // postId, userId를 통해서 participationEntity호출
        ParticipationEntity participationEntity = participationService
            .getParticipationEntityByPostIdAndUserId(postId, userId);

        // 배당금 반환 API 호출
        try {

        } catch (Exception e) {

        }

        // deposit 반환시점 기록
        participationService.setDateToReturnDeposit(participationEntity,
            LocalDate.now());

        // 상태를 변환하고 저장
        participationService.checkAndChangeStatusToSaveParticipation(
            participationEntity, List.of(ParticipationStatus.JOIN),
            List.of(ParticipationStatus.JOIN_CANCEL,
                DepositStatus.DEPOSIT_RETURNED));

    }


    // 3. 여행을 자진, 신고를 통해서 개인 취소하여 배당금 몰수
    @Transactional
    public void unjoinWithDepositTakenParticipation(Long postId,
        String userId) {

        // postId, userId를 통해서 participationEntity호출
        ParticipationEntity participationEntity = participationService
            .getParticipationEntityByPostIdAndUserId(postId, userId);

        // 배당금 몰수 API 호출
        try {

        } catch (Exception e) {

        }

        // 상태를 변환하고 저장
        participationService.checkAndChangeStatusToSaveParticipation(
            participationEntity, List.of(ParticipationStatus.JOIN),
            List.of(ParticipationStatus.JOIN_CANCEL,
                DepositStatus.DEPOSIT_TAKEN));
    }

    // 4. 인원이 시간이 지나서 여행을 완료하는 경우;
    public void travelFinishedParticipation(Long postId, String userId) {

        // 매일 게시글의 여행완료 시점을 확인 로직 추가

        // 식별한 participation을 호출
        ParticipationEntity participationEntity = participationService
            .getParticipationEntityByPostIdAndUserId(postId, userId);

        // 보증금 반환일자를 확정
        participationService.setDateToReturnDeposit(participationEntity,
            LocalDate.now().plusDays(30));

        // 참여의 상태를 변경 ; 여행완료
        participationService.checkAndChangeStatusToSaveParticipation(
            participationEntity,
            List.of(ParticipationStatus.JOIN),
            List.of(ParticipationStatus.TRAVEL_FINISHED));
    }


    // 5. 여행을 왼료한 후에 평점을 매김
    //5.1 평점을 보증금 기본반환일(여행완료 후 30일 이후)에 매긴 경우, 평점상태만 변경
    //5.2 평점을 여행완료 후 7일이거나 그 이후 그리고 30일이전에 매긴 경우, 다음날을 보증금 지급일 변경 ? 확인필요
    //5.3 평점을 여행완료 후 7일 전에 매긴 경우, 보증금 지급일을 여행완료 후 7일후로 변경(보증금 지급일을 -23일함)

    // todo : 아래의 의문사항이 있지만 일단 단순한 상황을 가정해서 코딩후 리팩토링필요
    // what if1. 여행보증금 지급일로 보증금지급시점을 관리하는데, 보증금 지급일 변경과 지급이 동시에
    // 이루어지는 경쟁상황은 어떻게 처리할 것인지? 정책적 해결, 기술적 해결.
    // what if2. 평점을 매긴 날짜가 여행완료후 7일이후일경우에 바로 보증금 반환 혹은 다음날 반환?


    public void giveRatingParticipation(Long postId, String userId) {

        ParticipationEntity entity = participationService
            .getParticipationEntityByPostIdAndUserId(postId, userId);

        if(entity.getDepositReturnDate()==null){
            throw new CustomException(ErrorCode.PARTICIPATION_STATUS_ERROR);
        }

        //5.1 평점을 보증금 기본반환일이나 그 이후(여행완료 후 30일 이후)에 매긴 경우, 평점상태만 변경
        if(LocalDate.now().isAfter(entity.getDepositReturnDate().minusDays(1))){
            participationService.checkAndChangeStatusToSaveParticipation(
                entity,
                List.of(ParticipationStatus.TRAVEL_FINISHED, RatingStatus.NOT_RATED), // 자정에 평가하는 문제때문에 우선 deposit 상태는 검증 x
                List.of(RatingStatus.RATED)
            );
        }

        //5.2 평점을 여행완료 후 7일이거나 그 이후 그리고 30일이전에 매긴 경우, 다음날을 보증금 지급일 변경 ? 확인필요

        if(LocalDate.now().isBefore(entity.getDepositReturnDate())
        &&LocalDate.now().isAfter(entity.getDepositReturnDate().minusDays(23))){

            // 보증금 반환일자를 다음날로 설정
            participationService.setDateToReturnDeposit(entity,
                LocalDate.now().plusDays(1));

            participationService.checkAndChangeStatusToSaveParticipation(
                entity,
                List.of(ParticipationStatus.TRAVEL_FINISHED, RatingStatus.NOT_RATED,
                    DepositStatus.DEPOSIT_PAID),
                List.of(RatingStatus.RATED)
            );
        }

        //5.3 평점을 여행완료 후 7일 전에 매긴 경우, 보증금 지급일을 여행완료 후 7일후로 변경(보증금 지급일을 -23일함)
        if(entity.getDepositReturnDate().isAfter(LocalDate.now())){

            participationService.setDateToReturnDeposit(entity,
                entity.getDepositReturnDate().minusDays(23));

            participationService.checkAndChangeStatusToSaveParticipation(
                entity,
                List.of(ParticipationStatus.TRAVEL_FINISHED, RatingStatus.NOT_RATED
                    ,DepositStatus.DEPOSIT_PAID),
                List.of(RatingStatus.RATED)
            );


        }


    }

    // 6. 여행을 왼료한 후에 보증금이 반환됨
    public void returnDepositAfterTravelParticipation(Long postId,
        String userId) {

        // 매일 보증금 반환시점을 확인하고 보증금 반환을 확인하는 logic 추가

        // 식별된 entity를 호출해옴
        ParticipationEntity participationEntity = participationService
            .getParticipationEntityByPostIdAndUserId(postId, userId);



        // 배당금 반환 API 호출
        try {

        } catch (Exception e) {

        }

        // 보증금 반환 상태를 변경후 저장

        participationService.checkAndChangeStatusToSaveParticipation(
            participationEntity,
            List.of(ParticipationStatus.TRAVEL_FINISHED, DepositStatus.DEPOSIT_PAID),
            List.of( DepositStatus.DEPOSIT_RETURNED)
        );



    }


}