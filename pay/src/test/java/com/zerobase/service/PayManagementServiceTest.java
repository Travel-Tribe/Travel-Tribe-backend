package com.zerobase.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zerobase.api.KakaopayApi;
import com.zerobase.api.ParticipationApi;
import com.zerobase.config.Constants;
import com.zerobase.entity.DepositEntity;
import com.zerobase.entity.PaymentEntity;
import com.zerobase.model.PaymentDto;
import com.zerobase.model.ResponseApi;
import com.zerobase.model.ResponseDepositPayDto;
import com.zerobase.model.type.PGMethod;
import com.zerobase.model.type.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PayManagementServiceTest {

    @Mock
    private DepositService depositService;
    @Mock
    private KakaopayApi kakaopayApi;
    @Mock
    private PaymentService paymentService;
    @Mock
    private ParticipationApi participationApi;
    @Mock
    private Constants constants;

    @InjectMocks
    private PayManagementService payManagementService;

    private final Long TEST_POST_ID = 13L;
    private final Long TEST_PARTICIPATION_ID = 49L;
    private final Long TEST_DEPOSIT_ID = 100L;
    private final String TEST_USER_ID = "testUser";
    private final String TEST_TID = "test_tid";
    private final String TEST_PG_TOKEN = "test_pg_token";

    private DepositEntity testDepositEntity;
    private PaymentEntity testPaymentEntity;
    private PaymentDto testPaymentDto;

    @BeforeEach
    void setUp() {
        testDepositEntity = DepositEntity.builder()
            .depositId(TEST_DEPOSIT_ID)
            .postId(TEST_POST_ID)
            .participationId(TEST_PARTICIPATION_ID)
            .userId(TEST_USER_ID)
            .paymentStatus(PaymentStatus.PAY_IN_PROGRESS)
            .build();

        testPaymentEntity = PaymentEntity.builder()
            .referentialOrderId(TEST_DEPOSIT_ID)
            .userId(TEST_USER_ID)
            .paykey(TEST_TID)
            .paymentStatus(PaymentStatus.PAY_IN_PROGRESS)
            .build();

        testPaymentDto = PaymentDto.builder()
            .amount(10000L)
            .build();
    }

    @Test
    @DisplayName("결제 준비 성공 테스트")
    void createDepositOrderAndInitiatePayTest() {
        ResponseApi.PayReadyApiDto payReadyApiDto = new ResponseApi.PayReadyApiDto();
        payReadyApiDto.setTid(TEST_TID);
        payReadyApiDto.setNextRedirectPcUrl("test-url");

        when(depositService.createAndSaveDepositOrder(TEST_POST_ID, TEST_PARTICIPATION_ID, TEST_USER_ID))
            .thenReturn(testDepositEntity);
        when(kakaopayApi.sendPayReadySign(TEST_POST_ID, TEST_DEPOSIT_ID, TEST_USER_ID))
            .thenReturn(payReadyApiDto);
        when(paymentService.createPaymentAndSave(TEST_DEPOSIT_ID, TEST_USER_ID, TEST_TID, PGMethod.KAKAOPAY))
            .thenReturn(com.zerobase.model.PaymentDto.builder().build());

        ResponseDepositPayDto result = payManagementService.createDepositOrderAndInitiatePay(
            TEST_POST_ID, TEST_PARTICIPATION_ID, TEST_USER_ID, PGMethod.KAKAOPAY);

        assertNotNull(result);
        assertEquals(TEST_DEPOSIT_ID, result.getDepositId());
        assertEquals(TEST_POST_ID, result.getPostId());
        assertEquals(TEST_PARTICIPATION_ID, result.getParticipationId());
    }

    @Test
    @DisplayName("결제 성공 처리 테스트")
    void clientSuccessDepositPayTest() {
        when(paymentService.getPaymentsInProgressAndChangeStatusByOrderId(TEST_DEPOSIT_ID, PaymentStatus.PAY_COMPLETED))
            .thenReturn(testPaymentEntity);
        when(depositService.getPaymentInProgressAndchangeStatusByOrderId(TEST_DEPOSIT_ID, PaymentStatus.PAY_COMPLETED))
            .thenReturn(testDepositEntity);

        assertDoesNotThrow(() ->
            payManagementService.clientSuccessDepositPay(TEST_DEPOSIT_ID, TEST_USER_ID, TEST_PG_TOKEN));

        verify(kakaopayApi).sendPayConfirmSign(TEST_TID, TEST_DEPOSIT_ID, TEST_USER_ID, TEST_PG_TOKEN);
        verify(participationApi).confirmParticipation(TEST_PARTICIPATION_ID, TEST_USER_ID);
        verify(paymentService).save(testPaymentEntity);
        verify(depositService).save(testDepositEntity);
    }

    @Test
    @DisplayName("결제 실패 처리 테스트")
    void clientFailedDepositPayTest() {
        when(depositService.getPaymentInProgressAndchangeStatusByOrderId(TEST_DEPOSIT_ID, PaymentStatus.PAY_FAILED))
            .thenReturn(testDepositEntity);
        when(paymentService.getPaymentsInProgressAndChangeStatusByOrderId(TEST_DEPOSIT_ID, PaymentStatus.PAY_FAILED))
            .thenReturn(testPaymentEntity);

        assertDoesNotThrow(() ->
            payManagementService.clientFailedDepositPay(TEST_USER_ID, TEST_DEPOSIT_ID));

        verify(participationApi).failedParticipation(TEST_PARTICIPATION_ID, TEST_USER_ID);
        verify(paymentService).save(testPaymentEntity);
        verify(depositService).save(testDepositEntity);
    }

    @Test
    @DisplayName("환불 처리 테스트")
    void refundDepositPayTest() {
        when(depositService.SetToRefundDepositPay(TEST_PARTICIPATION_ID, TEST_USER_ID))
            .thenReturn(testDepositEntity);
        when(paymentService.SetToRefundPayment(TEST_DEPOSIT_ID, TEST_USER_ID))
            .thenReturn(testPaymentEntity);

        assertDoesNotThrow(() ->
            payManagementService.refundDepositPay(TEST_PARTICIPATION_ID, TEST_USER_ID));

        verify(kakaopayApi).sendPayRefundSign(TEST_TID);
        verify(paymentService).save(testPaymentEntity);
        verify(depositService).save(testDepositEntity);
    }
}