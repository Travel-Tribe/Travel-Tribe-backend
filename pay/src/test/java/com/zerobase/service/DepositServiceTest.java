package com.zerobase.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.zerobase.api.ParticipationApi;
import com.zerobase.entity.DepositEntity;
import com.zerobase.exception.BizException;
import com.zerobase.exception.errorCode.PaymentErrorCode;
import com.zerobase.model.type.PaymentStatus;
import com.zerobase.repository.DepositRepository;
import com.zerobase.service.DepositService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class DepositServiceTest {

    @Mock
    private DepositRepository depositRepository;

    @Mock
    private ParticipationApi participationAPi;

    @InjectMocks
    private DepositService depositService;

    private DepositEntity testDepositEntity;
    private final Long TEST_POST_ID = 1L;
    private final Long TEST_PARTICIPATION_ID = 1L;
    private final String TEST_USER_ID = "testUser";

    @BeforeEach
    void setUp() {
        testDepositEntity = DepositEntity.builder()
            .postId(TEST_POST_ID)
            .participationId(TEST_PARTICIPATION_ID)
            .userId(TEST_USER_ID)
            .paymentStatus(PaymentStatus.PAY_IN_PROGRESS)
            .build();
    }

    @Test
    @DisplayName("주문 생성 및 저장 테스트")
    void createAndSaveDepositOrderTest() {
        when(depositRepository.save(any(DepositEntity.class))).thenReturn(testDepositEntity);

        DepositEntity result = depositService.createAndSaveDepositOrder(
            TEST_POST_ID, TEST_PARTICIPATION_ID, TEST_USER_ID);

        assertNotNull(result);
        assertEquals(TEST_POST_ID, result.getPostId());
        assertEquals(TEST_PARTICIPATION_ID, result.getParticipationId());
        assertEquals(TEST_USER_ID, result.getUserId());
        assertEquals(PaymentStatus.PAY_IN_PROGRESS, result.getPaymentStatus());

        verify(depositRepository, times(1)).save(any(DepositEntity.class));
    }

    @Test
    @DisplayName("주문 검증 성공 테스트")
    void validateDepositCreateRequestSuccessTest() {
        when(participationAPi.validateParticipationInfo(TEST_POST_ID, TEST_PARTICIPATION_ID, TEST_USER_ID))
            .thenReturn(ResponseEntity.ok(true));
        when(depositRepository.existsByParticipationIdAndPaymentStatus(
            TEST_PARTICIPATION_ID, PaymentStatus.PAY_COMPLETED)).thenReturn(false);

        assertDoesNotThrow(() ->
            depositService.validateDepositCreateRequest(TEST_POST_ID, TEST_PARTICIPATION_ID, TEST_USER_ID));
    }

    @Test
    @DisplayName("주문 검증 실패 테스트 - 유효하지 않은 참여 정보")
    void validateDepositCreateRequestFailByInvalidParticipationTest() {
        when(participationAPi.validateParticipationInfo(TEST_POST_ID, TEST_PARTICIPATION_ID, TEST_USER_ID))
            .thenReturn(ResponseEntity.ok(false));

        BizException exception = assertThrows(BizException.class, () ->
            depositService.validateDepositCreateRequest(TEST_POST_ID, TEST_PARTICIPATION_ID, TEST_USER_ID));

        assertEquals(PaymentErrorCode.INVALID_PARTICIPATION_INFORMATION, exception.getErrorCode());
    }

    @Test
    @DisplayName("주문 상태 변경 테스트")
    void getPaymentInProgressAndchangeStatusByOrderIdTest() {
        when(depositRepository.findById(TEST_PARTICIPATION_ID)).thenReturn(
            Optional.of(testDepositEntity));

        DepositEntity result = depositService.getPaymentInProgressAndchangeStatusByOrderId(
            TEST_PARTICIPATION_ID, PaymentStatus.PAY_COMPLETED);

        assertEquals(PaymentStatus.PAY_COMPLETED, result.getPaymentStatus());
        verify(depositRepository, times(1)).findById(TEST_PARTICIPATION_ID);
    }

    @Test
    @DisplayName("환불 처리 테스트")
    void setToRefundDepositPayTest() {
        DepositEntity completedPayment = DepositEntity.builder()
            .postId(TEST_POST_ID)
            .participationId(TEST_PARTICIPATION_ID)
            .userId(TEST_USER_ID)
            .paymentStatus(PaymentStatus.PAY_COMPLETED)
            .build();

        when(depositRepository.findByParticipationIdAndPaymentStatus(
            TEST_PARTICIPATION_ID, PaymentStatus.PAY_COMPLETED))
            .thenReturn(Optional.of(completedPayment));

        DepositEntity result = depositService.SetToRefundDepositPay(TEST_PARTICIPATION_ID, TEST_USER_ID);

        assertEquals(PaymentStatus.PAY_REFUNDED, result.getPaymentStatus());
        verify(depositRepository, times(1))
            .findByParticipationIdAndPaymentStatus(TEST_PARTICIPATION_ID, PaymentStatus.PAY_COMPLETED);
    }

    @Test
    @DisplayName("환불 처리 실패 테스트 - 존재하지 않는 주문")
    void setToRefundDepositPayFailByNotExistingTest() {
        when(depositRepository.findByParticipationIdAndPaymentStatus(
            TEST_PARTICIPATION_ID, PaymentStatus.PAY_COMPLETED))
            .thenReturn(Optional.empty());

        BizException exception = assertThrows(BizException.class, () ->
            depositService.SetToRefundDepositPay(TEST_PARTICIPATION_ID, TEST_USER_ID));

        assertEquals(PaymentErrorCode.DEPOSIT_NOT_EXSITING, exception.getErrorCode());
    }
}