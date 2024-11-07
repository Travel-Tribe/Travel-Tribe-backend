package com.zerobase.travel.service;

import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.type.RatingStatus;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;

    /*
       1. 인당 최대 두개 참여가능
       2. 게시글에서의 제한조건을 검사하여 가져옴
       3. 게시글의 상태가 현재 모집중인지 확인
     */

    public void validateApplicant(String userId) {
        log.info("participation validation service start ");


    }

    // 참여를 시키는 기능
    public ParticipationDto createParticipation(Long postId, String userId) {
        log.info("participation creation service start ");

        this.validateApplicant(userId);

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

    // 게시글의 참여자 목록을 보는 기능
    public List<ParticipationDto> getParticipationsStatusOfJoinAndJoinReady(
        Long postId) {
        log.info("participation getParticipationsStatusOfJoinAndJoinReady");

        List<ParticipationStatus> statuses =
            new ArrayList<>(
                List.of(ParticipationStatus.JOIN, ParticipationStatus.JOIN_READY));

        List<ParticipationEntity> participationEntities
            = participationRepository.findByPostEntityPostIdAndParticipationStatusIn(
            postId, statuses);

        return participationEntities.stream().map(ParticipationDto::fromEntity)
            .toList();
    }

    // 한명이 여행을 중도 포기하고 DEPOSIT을 반환받지못함
    public void unjoinWithDepositPenaltyParticipation(Long postId, String userId) {
        ParticipationEntity participationEntity = participationRepository.findByPostEntityPostIdAndUserId(
                postId, userId)
            .orElseThrow(
                () -> new CustomException(ErrorCode.PARTICIPATION_NOT_FOUND));

        participationEntity.setParticipationStatus(ParticipationStatus.UNJOIN);
        participationEntity.setDepositStatus(DepositStatus.DEPOSIT_TAKEN);
        participationRepository.save(participationEntity);
    }


    // 한명이 rating을 주는 경우
    public void giveRatingParticipation(Long postId, String userId) {
        ParticipationEntity participationEntity = participationRepository.findByPostEntityPostIdAndUserId(
                postId, userId)
            .orElseThrow(
                () -> new CustomException(ErrorCode.PARTICIPATION_NOT_FOUND));

        participationEntity.setRatingStatus(RatingStatus.RATED);
        participationRepository.save(participationEntity);
    }

    // 모든 인원이 여행을 완료하는 경우 ; saveAll할시에 error 발생
    public void travelFinishedParticipation(Long postId) {
        List<ParticipationEntity> participationEntities = participationRepository
            .findAllByPostEntityPostIdAndParticipationStatus(postId,
                ParticipationStatus.JOIN);

        for (ParticipationEntity participationEntity : participationEntities) {
            participationEntity.setParticipationStatus(ParticipationStatus.TRAVEL_FINISHED);
            participationRepository.save(participationEntity);
        }
    }

    // 모든 인원이 합의하에 여행을 취소하는 경우 ; saveAll할시에 error 발생
    public void unJoinAllWithouㅅPenalrtyParticipation(Long postId) {
        List<ParticipationEntity> participationEntities = participationRepository
            .findAllByPostEntityPostIdAndParticipationStatus(postId,
                ParticipationStatus.JOIN);

        for (ParticipationEntity participationEntity : participationEntities) {
            participationEntity.setParticipationStatus(ParticipationStatus.UNJOIN);
            participationEntity.setDepositStatus(DepositStatus.DEPOSIT_RETURNED);

            participationRepository.save(participationEntity);
        }
    }





}