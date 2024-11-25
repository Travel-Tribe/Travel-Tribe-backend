package com.zerobase.travel.service;

import com.zerobase.travel.api.PayApi;
import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.service.PostService;
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


/*
Participation Status를 변화시키는 모든 비즈니스 케이스 반영
1. 여행을 참가
1.1 여행을 참가를 눌렀지만 결제처리에서 에러가 난경우 :
1.1 여행 참가를 눌러서 결재 완료가 정상처리 된 경우 : pay모듈에서 수신
1.2 여행 참가를 눌러서 혹은 결제실패 처리가 정상처리가 된 경우 : pay모듈에서 수신
2. 여행을 투표를 통해서 전체 취소 : pay모듈로 발신
3. 여행을 자진, 신고를 통해서 개인 취소
4. 여행을 전체 완료 (평점안매김)
5. 여행을 왼료한 후에 평점을 매김
5.1 평점을 여행완료 후 7일 이후에 매긴 경우
5.2 평점을 여행완료 후 7일 전에 매긴 경우
6. 여행을 완료한 후에  보증금이 반환됨 : pay모듈로 발신


 */


@Service
@Slf4j
@RequiredArgsConstructor
public class ParticipationManagementService {


    private final ParticipationService participationService;
    private final PayApi payApi;
    private final PostService postService;


    private final int DEPOSIT_RETURN_DATE_DEFAULT = 30;
    private final int DEPOSIT_RETURN_DATE_SHORTENED_IF_RATED = 23;
    private final int NEXT_DAY = 1;
    private final int DEPOSIT_RETURN_IF_RATED = 1;




    // 1. 여행을 참가 ; 여행참가 ~ 지불까지를 하나의 트랜잭션으로 묶기에는 과도하게 길어서 분리.
    // JoinReady 상태 임시저장 -> client 에서 결제준비요청 -> 결제완료/실패시 서버에 응답
    public ParticipationDto readyParticipation(Long postId, String userId,
        String userEmail) {

        participationService.validateParticipationApplicant(postId, userId,
            userEmail);

        return participationService.createParticipationReady(
            postId, userId);
    }

    //1.1 여행을 참가를 눌렀지만 결제처리에서 에러가 난경우
    public void failedPaymentParticipation(long participationId,
        String userId) {

        // participation, userId를 통해서 participationEntity호출
        ParticipationEntity participationEntity = participationService
            .getParticipationByIdAndValidateUserId(participationId, userId);

        // fail에 따른 상태검증과 상태변경
        participationService.checkStatusParticipation(participationEntity, ParticipationEntity.beforePayStatuses);
        participationService.changeStatusParticipation(participationEntity, ParticipationEntity.afterPayFailStatuses);
        participationService.save(participationEntity);
    }

    // 1.2 여행 참가를 눌러서 결재 완료 정상처리가 된 경우
    @Transactional
    public void successPaymentParticipation(long participationId,
        String userId) {

        // participation, userId를 통해서 participationEntity호출
        ParticipationEntity entity = participationService
            .getParticipationByIdAndValidateUserId(participationId, userId);

        // confirm에 따른 상태 검증 및 변경
        participationService.checkStatusParticipation(entity, ParticipationEntity.beforePayStatuses);
        participationService.changeStatusParticipation(entity, ParticipationEntity.afterPaySuccessStatuses);

        participationService.save(entity);

        postService.changeStatusToRecruiting(
            entity.getPostEntity().getPostId());
    }

    //2. 여행을 투표를 통해서 취소, 투표완료시 해당 메소드 호출
    public void unjoinParticipationWithDepositReturned(Long postId,
        String userId) {

        // postId, userId를 통해서 participationEntity호출
        ParticipationEntity entity = participationService.getParticipationByPostIdAndUserId(postId, userId);

        // 상태를 검증하고 변환
        participationService.checkStatusParticipation(entity, ParticipationEntity.afterPaySuccessStatuses);
        participationService.changeStatusParticipation(entity, ParticipationEntity.afterVotingStatuses);


        // pay모듈에 취소요청
        payApi.payDepositRefund(entity.getParticipationId(), entity.getUserId());

        // deposit 반환시점 기록
        participationService.setDateToReturnDeposit(entity, LocalDate.now());

        // 검증 - 보증금반환까지 fail 이 안나면 저장
        participationService.save(entity);
    }

    // 3. 여행을 자진, 신고를 통해서 개인 취소하여 배당금 몰수
    public void unjoinParticipationWithDepositForfeited(Long postId,
        String userId) {

        // postId, userId를 통해서 participationEntity호출
        ParticipationEntity participationEntity = participationService
            .getParticipationByPostIdAndUserId(postId, userId);

        // 상태를 검증하고 변환
        participationService.checkStatusParticipation(participationEntity, ParticipationEntity.afterPaySuccessStatuses);
        participationService.changeStatusParticipation(participationEntity, ParticipationEntity.afterCancelStatuses);
        participationService.save(participationEntity);

    }


    // 4. 인원이 시간이 지나서 여행을 완료하는 경우;
    public void travelFinishedParticipations() {

        // 매일 게시글의 여행완료 시점을 확인 로직 추가

        List<ParticipationEntity> participationEntities
            = participationService.getParticipationOfPostIdOnDeadLine();

        // 참여의 상태를 검증하고 변경
        for (ParticipationEntity participationEntity : participationEntities) {

            participationService.checkStatusParticipation(participationEntity,ParticipationEntity.afterPaySuccessStatuses);
            participationService.changeStatusParticipation(participationEntity,ParticipationEntity.afterTravelFinishStatusesUnRated);

            // 보증금 반환일자를 확정
            participationService.setDateToReturnDeposit(participationEntity, LocalDate.now().plusDays(DEPOSIT_RETURN_DATE_DEFAULT));
        }

        participationService.saveParticipations(participationEntities);

    }

    // 5. 여행을 왼료한 후에 평점을 매김
    //5.1 평점을 보증금 기본반환일이나 그 이후(여행완료 후 30일 이후)에 매긴 경우, 평점상태만 변경
    //5.2 평점을 여행완료 후 7일이나 그 이후 그리고 30일이전에 매긴 경우, 다음날을 보증금 지급일 변경 ? 확인필요
    //5.3 평점을 여행완료 후 7일 전에 매긴 경우, 보증금 지급일을 여행완료 후 7일후로 변경(보증금 지급일을 -23일함)


    public void giveRatingParticipation(Long postId, String userId) {

        ParticipationEntity participationEntity = participationService
            .getParticipationByPostIdAndUserId(postId, userId);

        LocalDate travelEndDate = participationEntity.getPostEntity().getTravelEndDate();
        LocalDate returnDateDefault = travelEndDate.plusDays(DEPOSIT_RETURN_DATE_DEFAULT);
        LocalDate returnDateIfRated = travelEndDate.plusDays(DEPOSIT_RETURN_IF_RATED);



        // 보증금 일자가 정해지지 않았다면 여행이 미완료된 것으로 간주하여 fail
        if (participationEntity.getDepositReturnDate() == null) {
            throw new CustomException(ErrorCode.PARTICIPATION_STATUS_ERROR);
        }

        if(participationEntity.getRatingStatus()==RatingStatus.RATED) return;


        //5.1 보증금 기본 반환일이나 그 이후에 평가한 경우, 평점상태만 변경
        if (LocalDate.now().isAfter(returnDateDefault)|| LocalDate.now().isEqual(returnDateDefault)) {
            participationService.checkStatusParticipation(participationEntity,ParticipationEntity.afterTravelFinishStatusesUnRated);
            participationService.changeStatusParticipation(participationEntity,ParticipationEntity.afterTravelFinishStatusesRated);
        }

        //5.2 평점을 여행완료 후 7일이거나 그 이후 그리고 30일이전에 매긴 경우, 다음날을 보증금 지급일 변경
        if (LocalDate.now().isBefore(returnDateDefault) && LocalDate.now().isAfter(returnDateIfRated)) {
            participationService.setDateToReturnDeposit(participationEntity, LocalDate.now().plusDays(NEXT_DAY));
            participationService.checkStatusParticipation(participationEntity,ParticipationEntity.afterTravelFinishStatusesUnRated);
            participationService.changeStatusParticipation(participationEntity,ParticipationEntity.afterTravelFinishStatusesRated);
        }

        //5.3 평점을 여행완료 후 7일 전에 매긴 경우, 보증금 지급일을 여행완료 후 7일후로 변경
        if (LocalDate.now().isBefore(returnDateIfRated)) {
            participationService.setDateToReturnDeposit(participationEntity, returnDateIfRated);
            participationService.checkStatusParticipation(participationEntity,ParticipationEntity.afterTravelFinishStatusesUnRated);
            participationService.changeStatusParticipation(participationEntity,ParticipationEntity.afterTravelFinishStatusesRated);
        }
    }

    // 6 여행을 왼료한 후에 보증금이 반환됨
    public void returnDepositAfterTravelFinished(
        ParticipationEntity participationEntity) {

        // 보증금 반환 상태를 검증 후 상태 변환

        participationService.checkStatusParticipation(participationEntity,ParticipationEntity.afterTravelFinishStatusesExcludeRating);
        participationService.changeStatusParticipation(participationEntity,ParticipationEntity.afterDepositReturnedExcludeRating);

        // 배당금 반환 API 호출
        payApi.payDepositRefund(participationEntity.getParticipationId(), participationEntity.getUserId());

        participationService.save(participationEntity);
    }


}