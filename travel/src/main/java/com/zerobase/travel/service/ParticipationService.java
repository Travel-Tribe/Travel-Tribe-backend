package com.zerobase.travel.service;

import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.dto.ParticipationsDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.repository.PostRepository;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.type.RatingStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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
    private final PostRepository postRepository;

    /*
       1. 인당 최대 두개까지만 참여가능
       2. 포스트당 최대 참여자수를 넘겨서는 안될것
       3. 여러가지 취향에 따른 제한들
       4. 게시글의 상태가 현재 모집중인지 확인
     */

    // 참여를 초기화하고 생성시키는 기능
    // todo : lock 잠금처리 할 것

    public void validateParticipationApplicant(Long postId, String userId) {
        // 1. 인당 최대 두개까지만 참여가능

        if (this.countParticipationsJoinAndJoinReadyByUserId(userId) >= 2) {
            log.info("participation validation service start ");
            throw new CustomException(ErrorCode.USER_PARTICIPATION_LIMIT);
        }

        PostEntity postEntity = postRepository.findByPostId(postId).orElseThrow(
            () -> new CustomException(ErrorCode.POST_NOT_EXISTING));

        // 2. 포스트당 최대 참여자수를 넘겨서는 안될것
        if (this.countParticipationsJoinAndJoinReadyByPostId(postId)
            <= postEntity.getMaxParticipants()) {
            throw new CustomException(ErrorCode.POST_PARTICIPATION_LIMIT);
        }

        // 3. 여러가지 취향에 따른 제한들 - user 의 정보를 호출

        // 4. 게시글의 상태가 현재 모집중인지 확인



    }

    public ParticipationDto createParticipationReady(Long postId,
        String userId) {
        log.info("participation creation service start ");

        ParticipationEntity participationEntity = ParticipationEntity.builder()
            .postEntity(PostEntity.builder().postId(postId).build())
            .userId(userId)
            .participationStatus(ParticipationStatus.JOIN_READY)
            .ratingStatus(RatingStatus.NOT_RATED)
            .depositStatus(DepositStatus.UNPAID)
            .build();

        ParticipationEntity entity = participationRepository.save(
            participationEntity);

        return ParticipationDto.fromEntity(entity);
    }

    // participation 의 상태를 검증하고 상태를 변화시켜서 그대로 저장함
    public void checkAndChangeStatusParticipation(
        ParticipationEntity participationEntity
        , List<Enum<?>> changEnumFrom, List<Enum<?>> changEnumTo) {

        // entity의 status들을 list에 담음
        List<Enum<?>> statuses = participationEntity.getStatuses();

        // entity의 enum type을 순회하여 기대한 status와 다르면 예외발생
        for (Enum<?> status : statuses) {
            for (Enum<?> enumElement : changEnumFrom) {
                if (status.getClass() == enumElement.getClass()) {
                    if (status != enumElement) {
                        log.error("status is not as expected :" + status);
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

    }

    public void saveParticipation(ParticipationEntity participationEntity) {
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
    public List<ParticipationsDto> getParticipationsDtosStatusOfJoinAndJoinReady(
        Long postId) {
        log.info("participation getParticipationsStatusOfJoinAndJoin");

        List<ParticipationEntity> participationEntities
            = participationRepository.findAllByPostEntityPostIdAndParticipationStatusIn(
            postId, List.of(ParticipationStatus.JOIN,ParticipationStatus.JOIN_READY));

        return participationEntities.stream().map(ParticipationsDto::fromEntity)
            .toList();
    }


    //--------------------------- 데이터 count 메소드 ---------------------------//

    public int countParticipationsCompletedByUserId(String userId) {

        return participationRepository.countByUserIdAndParticipationStatusIn(
            userId, List.of(ParticipationStatus.TRAVEL_FINISHED));
    }
    public int countParticipationsJoinAndJoinReadyByUserId(String userId) {
        return participationRepository.countByUserIdAndParticipationStatusIn(
            userId,
            List.of(ParticipationStatus.JOIN, ParticipationStatus.JOIN_READY)
        );
    }
    private int countParticipationsJoinAndJoinReadyByPostId(long postId) {
        return participationRepository.countByPostEntityPostIdAndParticipationStatusIn(
            postId,
            List.of(ParticipationStatus.JOIN, ParticipationStatus.JOIN_READY));
    }

    public void setDateToReturnDeposit(ParticipationEntity entity,
        LocalDate time) {
        entity.setDepositReturnDate(time);

    }


    public ParticipationEntity validateParticipationUserId(long participationId, String userId) {
        ParticipationEntity participationEntity = participationRepository.findById(
            participationId).orElseThrow(()->new CustomException(ErrorCode.PARTICIPATION_NOT_FOUND));

        if(!Objects.equals(participationEntity.getUserId(), userId))
            throw new CustomException(ErrorCode.USER_UNAUTHORIZED_REQUEST);

        return participationEntity;
    }
}
