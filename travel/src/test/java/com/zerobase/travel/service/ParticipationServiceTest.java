package com.zerobase.travel.service;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.repository.PostRepository;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.type.RatingStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ParticipationServiceTest {

    @Mock
    private ParticipationRepository participationRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private ParticipationService participationService;


    private static final String USER_ID = "testUser";
    private static final Long POST_ID = 1L;
    private static final long PARTICIPATIONID = 10L;
    private static final ParticipationEntity entity = ParticipationEntity.builder()
        .postEntity(PostEntity.builder().build())
        .userId(USER_ID)
        .participationId(PARTICIPATIONID)
        .build();


    @Test
    void validateParticipationApplicant_shouldThrowException_whenUserParticipationLimitExceeded() {

        // Given mock result
        when(participationRepository.countByUserIdAndParticipationStatusIn(
            USER_ID,
            List.of(ParticipationStatus.JOIN, ParticipationStatus.JOIN_READY)))
            .thenReturn(2);

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () ->
            participationService.validateParticipationApplicant(POST_ID,
                USER_ID));

        assertEquals(ErrorCode.USER_PARTICIPATION_LIMIT,
            exception.getErrorCode());
    }

    @Test
    void validateParticipationApplicant_shouldThrowException_whenPostParticipationLimitEquals() {

        // Given count user's participation return 1
        when(participationRepository.countByUserIdAndParticipationStatusIn(
            USER_ID,
            List.of(ParticipationStatus.JOIN, ParticipationStatus.JOIN_READY)))
            .thenReturn(1);

        // given post  mock
        PostEntity postEntity = PostEntity.builder().build();
        postEntity.setMaxParticipants(5);

        when(postRepository.findByPostId(POST_ID)).thenReturn(
            Optional.of(postEntity));

        //given count post's participation mock

        when(
            participationRepository.countByPostEntityPostIdAndParticipationStatusIn(
                POST_ID, List.of(ParticipationStatus.JOIN,
                    ParticipationStatus.JOIN_READY)))
            .thenReturn(5);

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () ->
            participationService.validateParticipationApplicant(POST_ID,
                USER_ID));

        assertEquals(ErrorCode.POST_PARTICIPATION_LIMIT,
            exception.getErrorCode());
    }



    @Test
    void validateParticipationApplicant_shouldThrowException_whenPostParticipationLimitExceeded() {

        // Given count user's participation return 1
        when(participationRepository.countByUserIdAndParticipationStatusIn(
            USER_ID,
            List.of(ParticipationStatus.JOIN, ParticipationStatus.JOIN_READY)))
            .thenReturn(1);

        // given post  mock
        PostEntity postEntity = PostEntity.builder().build();
        postEntity.setMaxParticipants(4);

        when(postRepository.findByPostId(POST_ID)).thenReturn(
            Optional.of(postEntity));

        //given count post's participation mock

        when(
            participationRepository.countByPostEntityPostIdAndParticipationStatusIn(
                POST_ID, List.of(ParticipationStatus.JOIN,
                    ParticipationStatus.JOIN_READY)))
            .thenReturn(5);

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () ->
            participationService.validateParticipationApplicant(POST_ID,
                USER_ID));

        assertEquals(ErrorCode.POST_PARTICIPATION_LIMIT,
            exception.getErrorCode());
    }


    @Test
    void checkAndChangeStatusParticipation_shouldThrowException_whenStatusMismatch() {
        // Given participation entity
        ParticipationEntity participationEntity = new ParticipationEntity();
        participationEntity.setStatuses(
            List.of(ParticipationStatus.JOIN,DepositStatus.PAID,RatingStatus.RATED));
        participationEntity.setParticipationStatus(ParticipationStatus.JOIN);
        participationEntity.setDepositStatus(DepositStatus.PAID);
        participationEntity.setRatingStatus(RatingStatus.RATED);

        // given change from and to
        List<Enum<?>> changeFrom = List.of(ParticipationStatus.JOIN,DepositStatus.UNPAID,RatingStatus.RATED );
        List<Enum<?>> changeTo = List.of(ParticipationStatus.JOIN,DepositStatus.FORFEITED,RatingStatus.RATED);
        // When & Then
        CustomException exception = assertThrows(CustomException.class, () ->
            participationService.checkAndChangeStatusParticipation(
                participationEntity, changeFrom, changeTo));

        assertEquals(ErrorCode.PARTICIPATION_STATUS_ERROR,
            exception.getErrorCode());
    }

    @Test
    void checkAndChangeStatusParticipation_PassValidationAndChangeStatus() {
        // Given participation entity
        ParticipationEntity participationEntity = new ParticipationEntity();
        participationEntity.setStatuses(
            List.of(ParticipationStatus.JOIN,DepositStatus.PAID,RatingStatus.RATED));
        participationEntity.setParticipationStatus(ParticipationStatus.JOIN);
        participationEntity.setDepositStatus(DepositStatus.PAID);
        participationEntity.setRatingStatus(RatingStatus.RATED);


        // given change from and to
        List<Enum<?>> changeFrom = List.of(ParticipationStatus.JOIN,DepositStatus.PAID,RatingStatus.RATED );
        List<Enum<?>> changeTo = List.of(ParticipationStatus.JOIN,DepositStatus.FORFEITED,RatingStatus.RATED);

        // When
        participationService.checkAndChangeStatusParticipation(participationEntity,changeFrom,changeTo);

        //then

       assertEquals(DepositStatus.FORFEITED,participationEntity.getDepositStatus());
    }


    @Test
    void getParticipationEntityByPostIdAndUserId_shouldReturnParticipationEntity_whenFound() {
        // Given
        ParticipationEntity participationEntity = new ParticipationEntity();
        when(participationRepository.findByPostEntityPostIdAndUserId(POST_ID,
            USER_ID))
            .thenReturn(Optional.of(participationEntity));

        // When
        ParticipationEntity result = participationService.getParticipationEntityByPostIdAndUserId(
            POST_ID, USER_ID);

        // Then
        assertNotNull(result);
        assertEquals(participationEntity, result);
    }

    @Test
    void getParticipationEntityByPostIdAndUserId_shouldThrowException_whenNotFound() {
        // Given
        when(participationRepository.findByPostEntityPostIdAndUserId(POST_ID,
            USER_ID))
            .thenReturn(Optional.empty());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () ->
            participationService.getParticipationEntityByPostIdAndUserId(
                POST_ID, USER_ID));

        assertEquals(ErrorCode.PARTICIPATION_NOT_FOUND,
            exception.getErrorCode());
    }


    @Test
    void createParticipationReady_shouldReturnParticipationDto_whenValid() {
        // Given
        ParticipationEntity savedEntity = ParticipationEntity.builder()
            .postEntity(PostEntity.builder().postId(POST_ID).build())
            .userId(USER_ID)
            .participationStatus(ParticipationStatus.JOIN_READY)
            .ratingStatus(RatingStatus.NOT_RATED)
            .depositStatus(DepositStatus.UNPAID)
            .build();

        when(participationRepository.save(ArgumentMatchers.any(ParticipationEntity.class))).thenReturn(savedEntity);

        // When
        ParticipationDto result = participationService.createParticipationReady(POST_ID, USER_ID);

        // Then
        assertNotNull(result);
        assertEquals(USER_ID, result.getUserId());
        assertEquals(POST_ID, result.getPostId());
        assertEquals("참가준비", result.getParticipationStatus());
    }

    @Test
    void saveParticipation_shouldSaveEntitySuccessfully() {
        // Given
        ParticipationEntity entity = ParticipationEntity.builder().build();

        // When
        participationService.saveParticipation(entity);

        // Then
        verify(participationRepository, Mockito.times(1)).save(entity);
    }

    @Test
    void setDateToReturnDeposit_shouldSetDepositReturnDate() {
        // Given
        ParticipationEntity entity = new ParticipationEntity();
        LocalDate returnDate = LocalDate.now();

        // When
        participationService.setDateToReturnDeposit(entity, returnDate);

        // Then
        assertEquals(returnDate, entity.getDepositReturnDate());
    }

    @Test
    void validateParticipationUserId_shouldReturnEntity_whenUserIdMatches() {
        // Given
        when(participationRepository.findById(PARTICIPATIONID)).thenReturn(Optional.of(entity));

        // When
        ParticipationEntity result = participationService.validateParticipationUserId(PARTICIPATIONID, USER_ID);

        // Then
        assertNotNull(result);
        assertEquals(entity, result);
    }

    @Test
    void validateParticipationUserId_shouldThrowException_whenUserIdDoesNotMatch() {
        // Given
        ParticipationEntity entityWithDifferentUserId = ParticipationEntity.builder()
            .participationId(PARTICIPATIONID)
            .userId("differentUser")
            .build();

        when(participationRepository.findById(PARTICIPATIONID)).thenReturn(Optional.of(entityWithDifferentUserId));

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () ->
            participationService.validateParticipationUserId(PARTICIPATIONID, USER_ID));

        assertEquals(ErrorCode.USER_UNAUTHORIZED_REQUEST, exception.getErrorCode());
    }

}
