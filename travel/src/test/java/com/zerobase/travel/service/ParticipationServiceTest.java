package com.zerobase.travel.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.type.ParticipationStatus;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ParticipationServiceTest {


    @Mock
    private ParticipationRepository participationRepository;

    @InjectMocks
    private ParticipationService participationService;

    @Test
    void createParticipation_ShouldReturnParticipationDto() {
        // Given
        Long postId = 1L;
        String userId = "user1";
        ParticipationEntity mockEntity = ParticipationEntity.builder()
            .postEntity(PostEntity.builder().postId(postId).build())
            .userId(userId)
            .participationStatus(ParticipationStatus.JOIN)
            .build();

        when(participationRepository.save(any(ParticipationEntity.class))).thenReturn(mockEntity);

        // When
        ParticipationDto result = participationService.createParticipation(postId, userId);

        // Then
        assertNotNull(result, "The returned ParticipationDto should not be null.");
        assertEquals(postId, result.getPostId(), "The post ID should match the one given.");
        assertEquals(userId, result.getUserId(), "The user ID should match the one given.");

        verify(participationRepository, times(1)).save(any(ParticipationEntity.class));
    }

    @Test
    void getParticipationsStatusOfJoinAndJoinReady_ShouldReturnListOfDtos() {
        // Given
        Long postId = 1L;
        List<ParticipationEntity> mockEntities = new ArrayList<>();
        mockEntities.add(ParticipationEntity.builder()
            .postEntity(PostEntity.builder().postId(postId).build())
            .userId("user1")
            .participationStatus(ParticipationStatus.JOIN)
            .build());
        mockEntities.add(ParticipationEntity.builder()
            .postEntity(PostEntity.builder().postId(postId).build())
            .userId("user2")
            .participationStatus(ParticipationStatus.JOIN_READY)
            .build());

        when(participationRepository.findByPostEntityPostIdAndParticipationStatusIn(anyLong(), anyList())).thenReturn(mockEntities);

        // When
        List<ParticipationDto> result = participationService.getParticipationsStatusOfJoinAndJoinReady(postId);

        // Then
        assertNotNull(result, "The returned list of ParticipationDto should not be null.");
        assertEquals(2, result.size(), "The list should contain two ParticipationDto objects.");

        verify(participationRepository, times(1)).findByPostEntityPostIdAndParticipationStatusIn(anyLong(), anyList());
    }
}