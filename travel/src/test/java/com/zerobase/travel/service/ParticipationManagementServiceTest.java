import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zerobase.travel.api.PayApi;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.service.PostService;
import com.zerobase.travel.service.ParticipationManagementService;
import com.zerobase.travel.service.ParticipationService;
import com.zerobase.travel.type.RatingStatus;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    private ParticipationEntity mockParticipation;
    private static final Long POST_ID = 1L;
    private static final String USER_ID = "user123";
    private static final String USER_EMAIL = "user@example.com";
    private static final int DEPOSIT_RETURN_DATE_DEFAULT = 30;
    private static final int DEPOSIT_RETURN_IF_RATED = 7;
    private static final int NEXT_DAY = 1;

    @BeforeEach
    void setUp() {
        mockParticipation = new ParticipationEntity();
        mockParticipation.setParticipationId(POST_ID);
        mockParticipation.setUserId(USER_ID);
        mockParticipation.setPostEntity(PostEntity.builder().build());
        mockParticipation.getPostEntity().setTravelEndDate(LocalDate.now().minusDays(10));
        mockParticipation.setDepositReturnDate(LocalDate.now().plusDays(20));
        mockParticipation.setRatingStatus(RatingStatus.NOT_RATED);
    }

    @Test
    void readyParticipation_shouldReturnDto() {
        when(participationService.createParticipationReady(anyLong(), anyString())).thenReturn(new ParticipationDto());

        ParticipationDto result = participationManagementService.readyParticipation(POST_ID, USER_ID, USER_EMAIL);

        assertThat(result).isNotNull();
        verify(participationService).validateParticipationApplicant(POST_ID, USER_ID, USER_EMAIL);
        verify(participationService).createParticipationReady(POST_ID, USER_ID);
    }

    @Test
    void failedPaymentParticipation_shouldUpdateStatus() {
        when(participationService.getParticipationByIdAndValidateUserId(anyLong(), anyString()))
            .thenReturn(mockParticipation);

        participationManagementService.failedPaymentParticipation(POST_ID, USER_ID);

        verifyStatusChange(ParticipationEntity.beforePayStatuses, ParticipationEntity.afterPayFailStatuses);
    }

    @Test
    void successPaymentParticipation_shouldUpdateStatusAndPost() {
        when(participationService.getParticipationByIdAndValidateUserId(anyLong(), anyString()))
            .thenReturn(mockParticipation);

        participationManagementService.successPaymentParticipation(POST_ID, USER_ID);

        verifyStatusChange(ParticipationEntity.beforePayStatuses, ParticipationEntity.afterPaySuccessStatuses);
        verify(postService).changeStatusToRecruiting(mockParticipation.getPostEntity().getPostId());
    }

    @Test
    void unjoinParticipationWithDepositReturned_shouldUpdateStatusAndRefundDeposit() {
        when(participationService.getParticipationByPostIdAndUserId(anyLong(), anyString()))
            .thenReturn(mockParticipation);

        participationManagementService.unjoinParticipationWithDepositReturned(POST_ID, USER_ID);

        verifyStatusChange(ParticipationEntity.afterPaySuccessStatuses, ParticipationEntity.afterVotingStatuses);
        verify(payApi).payDepositRefund(mockParticipation.getParticipationId(), mockParticipation.getUserId());
        verify(participationService).setDateToReturnDeposit(mockParticipation, LocalDate.now());
    }

    @Test
    void unjoinParticipationWithDepositForfeited_shouldUpdateStatus() {
        when(participationService.getParticipationByPostIdAndUserId(anyLong(), anyString()))
            .thenReturn(mockParticipation);

        participationManagementService.unjoinParticipationWithDepositForfeited(POST_ID, USER_ID);

        verifyStatusChange(ParticipationEntity.afterPaySuccessStatuses, ParticipationEntity.afterCancelStatuses);
    }

    @Test
    void travelFinishedParticipations_shouldUpdateStatusAndSetReturnDate() {
        when(participationService.getParticipationOfPostIdOnDeadLine()).thenReturn(List.of(mockParticipation));

        participationManagementService.travelFinishedParticipations();

        verifyStatusChange(ParticipationEntity.afterPaySuccessStatuses, ParticipationEntity.afterTravelFinishStatusesUnRated);
        verify(participationService).setDateToReturnDeposit(mockParticipation, LocalDate.now().plusDays(DEPOSIT_RETURN_DATE_DEFAULT));
        verify(participationService).saveParticipations(List.of(mockParticipation));
    }

    @Test
    void giveRatingParticipation_shouldUpdateStatusAndAdjustReturnDate() {
        LocalDate travelEndDate = LocalDate.now().minusDays(15);
        LocalDate depositReturnDate = travelEndDate.plusDays(DEPOSIT_RETURN_DATE_DEFAULT);
        mockParticipation.setDepositReturnDate(depositReturnDate);
        mockParticipation.getPostEntity().setTravelEndDate(travelEndDate);

        when(participationService.getParticipationByPostIdAndUserId(POST_ID, USER_ID)).thenReturn(mockParticipation);

        participationManagementService.giveRatingParticipation(POST_ID, USER_ID);

        verifyStatusChange(ParticipationEntity.afterTravelFinishStatusesUnRated, ParticipationEntity.afterTravelFinishStatusesRated);
        verifyDepositReturnDateAdjustment(travelEndDate, depositReturnDate);
    }

    private void verifyStatusChange(List<Enum<?>> beforeStatuses, List<Enum<?>> afterStatuses) {
        verify(participationService).checkStatusParticipation(mockParticipation, beforeStatuses);
        verify(participationService).changeStatusParticipation(mockParticipation, afterStatuses);
        verify(participationService).saveParticipation(mockParticipation);
    }

    private void verifyDepositReturnDateAdjustment(LocalDate travelEndDate, LocalDate depositReturnDate) {
        LocalDate now = LocalDate.now();
        if (now.isBefore(depositReturnDate) && now.isAfter(travelEndDate.plusDays(DEPOSIT_RETURN_IF_RATED))) {
            verify(participationService).setDateToReturnDeposit(eq(mockParticipation), eq(now.plusDays(NEXT_DAY)));
        } else if (now.isBefore(travelEndDate.plusDays(DEPOSIT_RETURN_IF_RATED))) {
            verify(participationService).setDateToReturnDeposit(eq(mockParticipation), eq(travelEndDate.plusDays(DEPOSIT_RETURN_IF_RATED)));
        }
    }
}