package com.zerobase.travel.service;

import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.repository.ParticipationRepository;
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
       2. 최대 개수를 넘겨서는 안될것
     */
    public void validateApplicant() {
        log.info("participation validation service start ");



    }

    public ParticipationDto createParticipation(Long postId, String userId) {
        log.info("participation creation service start ");

        this.validateApplicant();

        ParticipationEntity participationEntity = ParticipationEntity.builder()
            .postEntity(PostEntity.builder().postId(postId).build())
            .userId(userId)
            .participationStatus(ParticipationStatus.JOIN)
            .build();

        return ParticipationDto.fromEntity(
            participationRepository.save(participationEntity));
    }

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


}