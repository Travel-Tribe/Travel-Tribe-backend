package com.zerobase.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zerobase.config.Constants;
import com.zerobase.entity.PaymentEntity;
import com.zerobase.exception.BizException;
import com.zerobase.model.PaymentDto;
import com.zerobase.model.type.OrderType;
import com.zerobase.model.type.PGMethod;
import com.zerobase.model.type.PaymentStatus;
import com.zerobase.repository.PaymentRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private Constants constants;

    @InjectMocks
    private PaymentService paymentService;

    private final Long TEST_DEPOSIT_ID = 1L;
    private final String TEST_USER_ID = "testUser";
    private final String TEST_PAY_KEY = "test_pay_key";
    private PaymentEntity testPaymentEntity;

    @BeforeEach
    void setUp() {
        testPaymentEntity = PaymentEntity.builder()
            .referentialOrderType(OrderType.DEPOSIT)
            .referentialOrderId(TEST_DEPOSIT_ID)
            .userId(TEST_USER_ID)
            .paykey(TEST_PAY_KEY)
            .amount(10000L)
            .paymentStatus(PaymentStatus.PAY_IN_PROGRESS)
            .pgMethod(PGMethod.KAKAOPAY)
            .build();
    }

    @Test
    @DisplayName("결제 생성 및 저장 테스트")
    void createPaymentAndSaveTest() {


        when(paymentRepository.save(any(PaymentEntity.class))).thenReturn(testPaymentEntity);

        PaymentDto result = paymentService.createPaymentAndSave(
            TEST_DEPOSIT_ID, TEST_USER_ID, TEST_PAY_KEY, PGMethod.KAKAOPAY);



        assertNotNull(result);
        assertEquals(TEST_DEPOSIT_ID, result.getReferentialOrderId());
        assertEquals(TEST_USER_ID, result.getUserId());
        assertEquals(TEST_PAY_KEY, result.getPayKey());
        assertEquals(PaymentStatus.PAY_IN_PROGRESS, result.getPaymentStatus());

        verify(paymentRepository).save(any(PaymentEntity.class));
    }

    @Test
    @DisplayName("결제 상태 변경 테스트")
    void changeStatusTest() {
        PaymentEntity result = paymentService.changeStatus(testPaymentEntity, PaymentStatus.PAY_COMPLETED);

        assertEquals(PaymentStatus.PAY_COMPLETED, result.getPaymentStatus());
    }

    @Test
    @DisplayName("사용자 ID로 결제 목록 조회 테스트")
    void getPaymentsByUserIdTest() {
        List<PaymentEntity> paymentEntities = Arrays.asList(testPaymentEntity);
        when(paymentRepository.findAllByUserId(TEST_USER_ID)).thenReturn(paymentEntities);

        List<PaymentDto> results = paymentService.getPaymentsByUserId(TEST_USER_ID);

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals(TEST_USER_ID, results.get(0).getUserId());

        verify(paymentRepository).findAllByUserId(TEST_USER_ID);
    }

    @Test
    @DisplayName("주문 ID로 진행 중인 결제 조회 및 상태 변경 테스트")
    void getPaymentsInProgressAndChangeStatusByOrderIdTest() {
        when(paymentRepository.findByReferentialOrderIdAndPaymentStatus(
            TEST_DEPOSIT_ID, PaymentStatus.PAY_IN_PROGRESS))
            .thenReturn(Optional.of(testPaymentEntity));

        PaymentEntity result = paymentService.getPaymentsInProgressAndChangeStatusByOrderId(
            TEST_DEPOSIT_ID, PaymentStatus.PAY_COMPLETED);

        assertEquals(PaymentStatus.PAY_COMPLETED, result.getPaymentStatus());
        verify(paymentRepository).findByReferentialOrderIdAndPaymentStatus(
            TEST_DEPOSIT_ID, PaymentStatus.PAY_IN_PROGRESS);
    }

    @Test
    @DisplayName("결제 환불 처리 테스트")
    void setToRefundPaymentTest() {
        testPaymentEntity.setPaymentStatus(PaymentStatus.PAY_COMPLETED);
        when(paymentRepository.findByReferentialOrderId(TEST_DEPOSIT_ID))
            .thenReturn(Optional.of(testPaymentEntity));

        PaymentEntity result = paymentService.SetToRefundPayment(TEST_DEPOSIT_ID, TEST_USER_ID);

        assertNotNull(result);
        assertEquals(TEST_USER_ID, result.getUserId());
        verify(paymentRepository).findByReferentialOrderId(TEST_DEPOSIT_ID);
    }

    @Test
    @DisplayName("결제 환불 처리 실패 테스트 - 잘못된 사용자")
    void setToRefundPaymentFailByInvalidUserTest() {
        testPaymentEntity.setPaymentStatus(PaymentStatus.PAY_COMPLETED);
        when(paymentRepository.findByReferentialOrderId(TEST_DEPOSIT_ID))
            .thenReturn(Optional.of(testPaymentEntity));

        assertThrows(BizException.class, () ->
            paymentService.SetToRefundPayment(TEST_DEPOSIT_ID, "wrongUser"));
    }

    @Test
    @DisplayName("결제 환불 처리 실패 테스트 - 잘못된 결제 상태")
    void setToRefundPaymentFailByInvalidStatusTest() {
        testPaymentEntity.setPaymentStatus(PaymentStatus.PAY_FAILED);
        when(paymentRepository.findByReferentialOrderId(TEST_DEPOSIT_ID))
            .thenReturn(Optional.of(testPaymentEntity));

        assertThrows(BizException.class, () ->
            paymentService.SetToRefundPayment(TEST_DEPOSIT_ID, TEST_USER_ID));
    }
}