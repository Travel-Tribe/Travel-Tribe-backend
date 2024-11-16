package com.zerobase.travel.service;

import com.zerobase.travel.api.UserApi;
import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.dto.ResponseParticipationsDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.repository.PostRepository;
import com.zerobase.travel.post.type.Gender;
import com.zerobase.travel.post.type.LimitSex;
import com.zerobase.travel.post.type.LimitSmoke;
import com.zerobase.travel.post.type.PostStatus;
import com.zerobase.travel.post.type.Smoking;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.type.RatingStatus;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private final UserApi userApi;

    /*
       1. 인당 최대 두개까지만 참여가능
       2. 포스트당 최대 참여자수를 넘겨서는 안될것
       3. 여러가지 취향에 따른 제한들
       4. 게시글의 상태가 현재 모집중인지 확인
     */

    // 참여를 초기화하고 생성시키는 기능
    // todo : lock 잠금처리 할 것

    public void validateParticipationApplicant(Long postId, String userId,
        String userEmail) {

        // 비교대상인 postentity 와 userinfo 호출
        PostEntity postEntity = postRepository.findByPostId(postId).orElseThrow(
            () -> new CustomException(ErrorCode.POST_NOT_EXISTING));

        UserInfoResponseDTO userInfo = userApi.getUserInfoByUserEmail(userEmail)
            .getBody().getData();


        //  유저의 참여중인 정보를 호출
        List<ParticipationDto> participations =
            this.findParticipationsJoinAndJoinReadyByUserId(userId);


        // 1.1 유저는 최대 두개까지만 참여가능
        if (participations.size() >= 2) {
            log.info("participation validation service start ");
            throw new CustomException(ErrorCode.USER_PARTICIPATION_LIMIT);
        }

        // 1.2 유저는 이미 참여중이면 다시 참여할수 없음
        for (ParticipationDto participation : participations) {
            if(Objects.equals(participation.getPostId(), postId)){
                throw new CustomException(ErrorCode.PARTICIPATION_ALREADY_MADE);
            }
        }

        // 1.3 PayReady인 상태가 하나가 넘어서는 안됨
        for (ParticipationDto participation : participations) {
            if(Objects.equals(participation.getParticipationStatus(), ParticipationStatus.JOIN_READY)){
                throw new CustomException(ErrorCode.PARTICIPATION_JOINREADY_ALREADYEXISTING);
            }
        }


        // 2. 포스트당 최대 참여자수를 넘겨서는 안될것
        if (this.countParticipationsJoinAndJoinReadyByPostId(postId)
            >= postEntity.getMaxParticipants()) {
            throw new CustomException(ErrorCode.POST_PARTICIPATION_LIMIT);
        }

        // 3. post 제한사항과 user 프로필 필터링 여러가지 취향에 따른 제한들 - user 의 정보를 호출

        validatePostLimitAndUserProfile(userInfo, postEntity);

        // 4. 게시글의 상태가 현재 모집중인지 확인

        if (postEntity.getStatus() != PostStatus.RECRUITING) {
            throw new CustomException(ErrorCode.POST_STATUS_NOTRECRUITING);
        }


    }

    private static void validatePostLimitAndUserProfile(
        UserInfoResponseDTO userInfo, PostEntity postEntity) {

        // 나이검증
        int userAge = Period.between(userInfo.getBirth(), LocalDate.now()).getYears();


        if (postEntity.getLimitMinAge() > userAge
            || postEntity.getLimitMaxAge() < userAge)
            throw new CustomException(ErrorCode.POST_PARTICIPATION_LIMIT);

        // 성별검증

        if (postEntity.getLimitSex().equals(LimitSex.FEMALE)) {
           if(userInfo.getGender().equals(Gender.FEMALE)){
                throw new CustomException(ErrorCode.POST_PARTICIPATION_LIMIT);
            }
        } else if (postEntity.getLimitSex().equals(LimitSex.MALE)) {
            if(userInfo.getGender().equals(Gender.MALE)){
                throw new CustomException(ErrorCode.POST_PARTICIPATION_LIMIT);
            }
        }

        // 흡연자 검증
        if(postEntity.getLimitSmoke().equals(LimitSmoke.SMOKER)){
            if(userInfo.getSmoking().equals(Smoking.YES)){
                throw new CustomException(ErrorCode.POST_PARTICIPATION_LIMIT);
            }
        }else if(postEntity.getLimitSmoke().equals(LimitSmoke.NON_SMOKER)){
            if(userInfo.getSmoking().equals(Smoking.NO)){
                throw new CustomException(ErrorCode.POST_PARTICIPATION_LIMIT);
            }
        }

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
        for (Enum<?> enumOfEntity : statuses) {
            for (Enum<?> checkEnumData : changEnumFrom) {
                // entity의 enum이 check 하고자하는 enum과 동일한 타입일 경우 검증로직 실행
                if (enumOfEntity.getClass() == checkEnumData.getClass()) {
                    if (enumOfEntity != checkEnumData) {
                        log.error("status is not as expected :" + enumOfEntity);
                        throw new CustomException(
                            ErrorCode.PARTICIPATION_STATUS_ERROR);
                    }
                }
            }
        }

        // entity의 enum data를 changeEnumTo의 값으로 변경
        for (Enum<?> enumToInput : changEnumTo) {
            //  enum값의 type을 검증하여 어떤 타입인지 확인한다.
            if (enumToInput instanceof ParticipationStatus) {
                participationEntity.setParticipationStatus(
                    (ParticipationStatus) enumToInput);
            } else if (enumToInput instanceof DepositStatus) {
                participationEntity.setDepositStatus(
                    (DepositStatus) enumToInput);
            } else if (enumToInput instanceof RatingStatus) {
                participationEntity.setRatingStatus(
                    (RatingStatus) enumToInput);
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
    public List<ResponseParticipationsDto> getParticipationsDtosStatusOfJoin(
        Long postId) {
        log.info("participation getParticipationsStatusOfJoinAndJoin");

        List<ParticipationEntity> participationEntities
            = participationRepository.findAllByPostEntityPostIdAndParticipationStatusIn(
            postId, List.of(ParticipationStatus.JOIN,ParticipationStatus.JOIN_READY));

        return participationEntities.stream().map(ResponseParticipationsDto::fromEntity)
            .toList();
    }




    //--------------------------- 데이터 count 메소드 ---------------------------//

    public int countParticipationsCompletedByUserId(String userId) {

        return participationRepository.countByUserIdAndParticipationStatusIn(
            userId, List.of(ParticipationStatus.TRAVEL_FINISHED));
    }
    public List<ParticipationDto> findParticipationsJoinAndJoinReadyByUserId(String userId) {
        List<ParticipationEntity> participationEntities = participationRepository.findAllByUserIdAndParticipationStatusIn(
            userId,
            List.of(ParticipationStatus.JOIN, ParticipationStatus.JOIN_READY));

        return participationEntities.stream().map(ParticipationDto::fromEntity).toList();

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

    public Boolean validateParticipationUserIdAndPostId(long postId, long participationId,
        String userId) {

        Optional<ParticipationEntity> optioinal = participationRepository.findById(
            participationId);

        if(optioinal.isEmpty()) return false;

        ParticipationEntity participationEntity = optioinal.get();

        if(participationEntity.getPostEntity().getPostId() !=postId
            || !Objects.equals(participationEntity.getUserId(), userId)) {
            return false;
        }

        return true;
    }

    public List<ParticipationEntity> findParticipationToCompleteByNow(){

        List<PostEntity> postEntities = postRepository.findAllPostByDeadlineAndStatus(
            LocalDate.now(), PostStatus.RECRUITMENT_COMPLETED);

        List<ParticipationEntity> allByPostEntityIn = participationRepository.findAllByPostEntityIn(
            postEntities);

        return allByPostEntityIn;
    }


    public void saveParticipations(List<ParticipationEntity> participationEntities) {
        participationRepository.saveAll(participationEntities);
    }
}
