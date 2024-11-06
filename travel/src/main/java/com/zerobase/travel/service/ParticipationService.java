package com.zerobase.travel.service;

import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.type.ParticipationStatus;
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
       3. 여러가지 취향에 따른 제한들
     */
    public void validateApplicant(String userId) {
        if(this.countParticipationsJoinByUserId(userId)>=2){
            throw new CustomException(ErrorCode.PARTICIPATION_LIMIT);
        }


    }

    public ParticipationDto createParticipation(Long postId, String userId) {

        this.validateApplicant(userId);

        ParticipationEntity participationEntity = ParticipationEntity.builder()
            .postEntity(PostEntity.builder().postId(postId).build())
            .userId(userId)
            .participationStatus(ParticipationStatus.JOIN)
            .build();

        return ParticipationDto.fromEntity(
            participationRepository.save(participationEntity));
    }

    public List<ParticipationDto> getParticipationsStatusOfJoin(
        Long postId) {


        List<ParticipationEntity> participationEntities
            = participationRepository.findByPostEntityPostIdAndParticipationStatus(
            postId, ParticipationStatus.JOIN);

        return participationEntities.stream().map(ParticipationDto::fromEntity)
            .toList();
    }


    public int countParticipationsCompletedByUserId(String userId) {

        return participationRepository.countByParticipationStatusAndUserId(ParticipationStatus.TRAVEL_FINISHED,userId);
    }

    public int countParticipationsJoinByUserId(String userId) {

        return participationRepository.countByParticipationStatusAndUserId(ParticipationStatus.JOIN,userId);
    }
}