package com.zerobase.travel.service;

import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.dto.ParticipationsDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.type.RatingStatus;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;




/* Participation Status를 변화시키는 모든 비즈니스 케이스
1. 여행을 참가
2. 여행을 투표를 통해서 전체 취소
3. 여행을 자진, 신고를 통해서 개인 취소
4. 여행을 전체 완료
5. 여행을 왼료한 후에 평점을 매김
6. 여행을 왼료한 후에 평점을 안매김
 */


@Service
@Slf4j
@RequiredArgsConstructor
public class ParticipationService {


    private final ParticipationRepository participationRepository;

    /*
       1. 인당 최대 두개 참여가능
       2. 최대 개수를 넘겨서는 안될것
       3. 여러가지 취향에 따른 제한들
              3. 게시글의 상태가 현재 모집중인지 확인
     */
    public void validateParticipationApplicant(String userId) {
        if (this.countParticipationsJoinByUserId(userId) >= 2) {
            log.info("participation validation service start ");
            throw new CustomException(ErrorCode.PARTICIPATION_LIMIT);
        }


    }

    // 참여를 초기화하고 생성시키는 기능
    // todo : lock 잠금처리 할 것
    public ParticipationDto createParticipation(Long postId, String userId) {
        log.info("participation creation service start ");

        this.validateParticipationApplicant(userId);

        ParticipationEntity participationEntity = ParticipationEntity.builder()
            .postEntity(PostEntity.builder().postId(postId).build())
            .userId(userId)
            .participationStatus(ParticipationStatus.JOIN)
            .ratingStatus(RatingStatus.NOT_RATED)
            .depositStatus(DepositStatus.DEPOSIT_PAID)
            .build();

        return ParticipationDto.fromEntity(
            participationRepository.save(participationEntity));
    }

    // participation 의 상태를 검증하고 상태를 변화시켜서 그대로 저장함
    public void checkAndChangeStatusToSaveParticipation(
        ParticipationEntity participationEntity
        , List<Enum<?>> changEnumFrom, List<Enum<?>> changEnumTo) {

        // entity의 status들을 list에 담음
        List<Enum<?>> statuses = participationEntity.getStatuses();

        // entity의 enum type을 순회하여 기대한 status와 다르면 예외발생
        for (Enum<?> status : statuses) {
            for (Enum<?> enumElement : changEnumFrom) {
                if (status.getClass() == enumElement.getClass()) {
                    if (status != enumElement) {
                        throw new CustomException(
                            ErrorCode.PARTICIPATION_STATUS_ERROR);
                    }
                }
            }
        }

        // entity의 enum type을 원하는 값으로 변경
        for (Enum<?> enumElement : changEnumTo) {
            if (enumElement instanceof ParticipationStatus) {
                participationEntity.setParticipationStatus(
                    (ParticipationStatus) enumElement);
            } else if (enumElement instanceof DepositStatus) {
                participationEntity.setDepositStatus(
                    (DepositStatus) enumElement);
            } else if (enumElement instanceof RatingStatus) {
                participationEntity.setRatingStatus(
                    (RatingStatus) enumElement);
            }
        }

        participationRepository.save(participationEntity);
    }

    //--------------------------- 데이터 load 메소드 ---------------------------//

    // 현재 개별인원 엔티티 반환
    public ParticipationEntity getParticipationEntityByPostIdAndUserId(
        Long postId, String userId) {
        log.info("participation getParticipationEntityByPostIdAndUserId");

        return participationRepository.findByPostEntityPostIdAndUserId(
            postId, userId).orElseThrow(
            () -> new CustomException(ErrorCode.PARTICIPATION_NOT_FOUND));





    }


    // 현재 여행을 참여하고 있는 복수 인원리스트 반환
    public List<ParticipationsDto> getParticipationsDtosStatusOfJoin(
        Long postId) {
        log.info("participation getParticipationsStatusOfJoinAndJoin");

        List<ParticipationEntity> participationEntities
            = participationRepository.findAllByPostEntityPostIdAndParticipationStatus(
            postId, ParticipationStatus.JOIN);

        return participationEntities.stream().map(ParticipationsDto::fromEntity)
            .toList();
    }


    public int countParticipationsCompletedByUserId(String userId) {

        return participationRepository.countByParticipationStatusAndUserId(
            ParticipationStatus.TRAVEL_FINISHED, userId);
    }

    public int countParticipationsJoinByUserId(String userId) {

        return participationRepository.countByParticipationStatusAndUserId(
            ParticipationStatus.JOIN, userId);
    }

    public void setDateToReturnDeposit(ParticipationEntity entity, LocalDate time) {
        entity.setDepositReturnDate(time);

    }
}
