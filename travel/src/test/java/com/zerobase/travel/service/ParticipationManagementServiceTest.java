package com.zerobase.travel.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.zerobase.travel.api.PayApi;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.service.PostService;
import com.zerobase.travel.type.RatingStatus;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ParticipationManagementServiceTest {

    @InjectMocks
    private ParticipationManagementService participationManagementService;

    @Mock
    private ParticipationService participationService;

    @Mock
    private PayApi payApi;

    @Mock
    private PostService postService;


    private final int DEPOSIT_RETURN_DATE_DEFAULT = 30;
    private final int DEPOSIT_RETURN_DATE_SHORTENED_IF_RATED = 23;
    private final int NEXT_DAY = 1;
    private final int DEPOSIT_RETURN_IF_RATED = 1;

    private ParticipationEntity mockParticipationEntity;

    @BeforeEach
    void setUp() {
        mockParticipationEntity = new ParticipationEntity();
        mockParticipationEntity.setParticipationId(1L);
        mockParticipationEntity.setUserId("user123");
        mockParticipationEntity.setPostEntity(PostEntity.builder().build());
        mockParticipationEntity.getPostEntity().setTravelEndDate(LocalDate.now().minusDays(10));
        mockParticipationEntity.setDepositReturnDate(LocalDate.now().plusDays(20));
        mockParticipationEntity.setRatingStatus(RatingStatus.NOT_RATED);
    }

    @Test
    void givenValidData_whenReadyParticipation_thenReturnDto() {
        when(participationService.createParticipationReady(anyLong(), anyString())).thenReturn(new ParticipationDto());

        ParticipationDto result = participationManagementService.readyParticipation(1L, "user123", "user@example.com");

        assertThat(result).isNotNull();
        verify(participationService).validateParticipationApplicant(1L, "user123", "user@example.com");
        verify(participationService).createParticipationReady(1L, "user123");
    }

    @Test
    void givenValidData_whenFailedPayment_thenVerifyStatusChange() {
        when(participationService.getParticipationByIdAndValidateUserId(anyLong(), anyString()))
            .thenReturn(mockParticipationEntity);

        participationManagementService.failedPaymentParticipation(1L, "user123");

        verify(participationService).checkStatusParticipation(mockParticipationEntity, ParticipationEntity.beforePayStatuses);
        verify(participationService).changeStatusParticipation(mockParticipationEntity, ParticipationEntity.afterPayFailStatuses);
        verify(participationService).saveParticipation(mockParticipationEntity);
    }

    @Test
    void givenValidData_whenSuccessPayment_thenVerifyStatusChange() {
        when(participationService.getParticipationByIdAndValidateUserId(anyLong(), anyString()))
            .thenReturn(mockParticipationEntity);

        participationManagementService.successPaymentParticipation(1L, "user123");

        verify(participationService).checkStatusParticipation(mockParticipationEntity, ParticipationEntity.beforePayStatuses);
        verify(participationService).changeStatusParticipation(mockParticipationEntity, ParticipationEntity.afterPaySuccessStatuses);
        verify(participationService).saveParticipation(mockParticipationEntity);
        verify(postService).changeStatusToRecruiting(mockParticipationEntity.getPostEntity().getPostId());
    }

    @Test
    void givenValidData_whenUnjoinWithDepositReturned_thenVerifyActions() {
        when(participationService.getParticipationByPostIdAndUserId(anyLong(), anyString()))
            .thenReturn(mockParticipationEntity);

        participationManagementService.unjoinParticipationWithDepositReturned(1L, "user123");

        verify(participationService).checkStatusParticipation(mockParticipationEntity, ParticipationEntity.afterPaySuccessStatuses);
        verify(participationService).changeStatusParticipation(mockParticipationEntity, ParticipationEntity.afterVotingStatuses);
        verify(payApi).payDepositRefund(mockParticipationEntity.getParticipationId(), mockParticipationEntity.getUserId());
        verify(participationService).setDateToReturnDeposit(mockParticipationEntity, LocalDate.now());
        verify(participationService).saveParticipation(mockParticipationEntity);
    }

    @Test
    void givenValidData_whenUnjoinWithDepositForfeited_thenVerifyActions() {
        when(participationService.getParticipationByPostIdAndUserId(anyLong(), anyString()))
            .thenReturn(mockParticipationEntity);

        participationManagementService.unjoinParticipationWithDepositForfeited(1L, "user123");

        verify(participationService).checkStatusParticipation(mockParticipationEntity, ParticipationEntity.afterPaySuccessStatuses);
        verify(participationService).changeStatusParticipation(mockParticipationEntity, ParticipationEntity.afterCancelStatuses);
        verify(participationService).saveParticipation(mockParticipationEntity);
    }

    @Test
    void givenValidData_whenTravelFinishedParticipations_thenVerifyActions() {
        when(participationService.getParticipationOfPostIdOnDeadLine()).thenReturn(List.of(mockParticipationEntity));

        participationManagementService.travelFinishedParticipations();

        verify(participationService).checkStatusParticipation(mockParticipationEntity, ParticipationEntity.afterPaySuccessStatuses);
        verify(participationService).changeStatusParticipation(mockParticipationEntity, ParticipationEntity.afterTravelFinishStatusesUnRated);
        verify(participationService).setDateToReturnDeposit(mockParticipationEntity, LocalDate.now().plusDays(30));
        verify(participationService).saveParticipations(List.of(mockParticipationEntity));
    }


}