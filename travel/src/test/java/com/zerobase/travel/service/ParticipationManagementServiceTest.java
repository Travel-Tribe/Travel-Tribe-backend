package com.zerobase.travel.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.zerobase.travel.api.PayApi;
import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.type.RatingStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ParticipationManagementServiceTest {

    @Mock
    private ParticipationService participationService;

    @Mock
    private PayApi payApi;

    @InjectMocks
    private ParticipationManagementService participationManagementService;

    private static final Long POST_ID = 1L;
    private static final String USER_ID = "testUser";
    private static final long PARTICIPATION_ID = 10L;
    private static final ParticipationEntity participationEntity
        = ParticipationEntity.builder()
        .participationId(PARTICIPATION_ID)
        .participationStatus(ParticipationStatus.JOIN_READY)
        .depositStatus(DepositStatus.PAID)
        .ratingStatus(RatingStatus.RATED)
        .depositReturnDate(LocalDate.now().plusDays(30L))
        .build();

    /*
    @Test
    void readyParticipation_shouldCreateParticipation_whenValid() {
        // Given
        ParticipationDto expectedParticipationDto = new ParticipationDto();
        doNothing().when(participationService).validateParticipationApplicant(POST_ID, USER_ID);
        when(participationService.createParticipationReady(POST_ID, USER_ID)).thenReturn(expectedParticipationDto);

        // When
        ParticipationDto result = participationManagementService.readyParticipation(POST_ID, USER_ID);

        // Then
        assertEquals(expectedParticipationDto, result);
    }

     */

    @Test
    void failedPaymentParticipation_shouldUpdateStatusToJoinFailed() {
        // Given
        ParticipationEntity participationEntity = new ParticipationEntity();
        when(participationService.validateParticipationUserId(PARTICIPATION_ID, USER_ID)).thenReturn(participationEntity);

        // When
        participationManagementService.failedPaymentParticipation(PARTICIPATION_ID, USER_ID);

        // Then
        verify(participationService).checkAndChangeStatusParticipation(
            participationEntity,
            List.of(ParticipationStatus.JOIN_READY, DepositStatus.UNPAID),
            List.of(ParticipationStatus.JOIN_FAILED));
        verify(participationService).saveParticipation(participationEntity);
    }

    @Test
    void successPaymentParticipation_shouldUpdateStatusToJoinAndPaid() {
        // Given
        ParticipationEntity participationEntity = new ParticipationEntity();
        when(participationService.validateParticipationUserId(PARTICIPATION_ID, USER_ID)).thenReturn(participationEntity);

        // When
        participationManagementService.successPaymentParticipation(PARTICIPATION_ID, USER_ID);

        // Then
        verify(participationService).checkAndChangeStatusParticipation(
            participationEntity,
            List.of(ParticipationStatus.JOIN_READY, DepositStatus.UNPAID),
            List.of(ParticipationStatus.JOIN, DepositStatus.PAID));
        verify(participationService).saveParticipation(participationEntity);
    }

    @Test
    void unjoinParticipationWithDepositReturned_shouldCallPayApiAndSaveParticipation() {
        // Given mock participationservice


        when(participationService.getParticipationEntityByPostIdAndUserId(POST_ID, USER_ID)).thenReturn(participationEntity);


        // When
        participationManagementService.unjoinParticipationWithDepositReturned(POST_ID, USER_ID);

        // Then
        verify(participationService).checkAndChangeStatusParticipation(
            participationEntity,
            List.of(ParticipationStatus.JOIN, DepositStatus.PAID),
            List.of(ParticipationStatus.JOIN_CANCEL, DepositStatus.RETURNED));
        verify(payApi).payDepositRefund(participationEntity.getParticipationId());
        verify(participationService).setDateToReturnDeposit(eq(participationEntity), any(LocalDate.class));
        verify(participationService).saveParticipation(participationEntity);
    }

    @Test
    void unjoinParticipationWithDepositForfeited_shouldUpdateStatusToJoinCancelAndForfeited() {
        // Given
        ParticipationEntity participationEntity = new ParticipationEntity();
        when(participationService.getParticipationEntityByPostIdAndUserId(POST_ID, USER_ID)).thenReturn(participationEntity);

        // When
        participationManagementService.unjoinParticipationWithDepositForfeited(POST_ID, USER_ID);

        // Then
        verify(participationService).checkAndChangeStatusParticipation(
            participationEntity,
            List.of(ParticipationStatus.JOIN, DepositStatus.PAID),
            List.of(ParticipationStatus.JOIN_CANCEL, DepositStatus.FORFEITED));
        verify(participationService).saveParticipation(participationEntity);
    }

    @Test
    void travelFinishedParticipation_shouldUpdateStatusToTravelFinished() {
        // Given
        ParticipationEntity participationEntity = new ParticipationEntity();
        when(participationService.getParticipationEntityByPostIdAndUserId(POST_ID, USER_ID)).thenReturn(participationEntity);

        // When
        participationManagementService.travelFinishedParticipation(POST_ID, USER_ID);

        // Then
        verify(participationService).checkAndChangeStatusParticipation(
            participationEntity,
            List.of(ParticipationStatus.JOIN),
            List.of(ParticipationStatus.TRAVEL_FINISHED));
        verify(participationService).setDateToReturnDeposit(eq(participationEntity), any(LocalDate.class));
        verify(participationService).saveParticipation(participationEntity);
    }

    @Test
    void returnDepositAfterTravelFinished_shouldUpdateStatusToReturnedAndCallPayApi() {
        // Given

        when(participationService.getParticipationEntityByPostIdAndUserId(POST_ID, USER_ID)).thenReturn(participationEntity);

        // When
        participationManagementService.returnDepositAfterTravelFinished(POST_ID, USER_ID);

        // Then
        verify(participationService).checkAndChangeStatusParticipation(
            participationEntity,
            List.of(ParticipationStatus.TRAVEL_FINISHED, DepositStatus.PAID),
            List.of(DepositStatus.RETURNED));
        verify(payApi).payDepositRefund(participationEntity.getParticipationId());
        verify(participationService).saveParticipation(participationEntity);
    }
}
