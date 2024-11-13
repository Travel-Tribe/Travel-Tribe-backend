package com.zerobase.service;

import static com.zerobase.model.type.PGMethod.KAKAOPAY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zerobase.entity.PaymentEntity;
import com.zerobase.model.type.OrderType;
import com.zerobase.model.type.PGMethod;
import com.zerobase.model.PaymentDto;
import com.zerobase.model.type.PaymentStatus;
import com.zerobase.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    // given variables
    private static final String USER_ID = "testUser";
    private static final String PAY_KEY = "testPayKey";
    private static final Long REFERENCIAL_ORDER_ID = 100L;
    private static final Long DEPOSIT_AMOUNT = 100000L;
    private static final PGMethod pgMethod = KAKAOPAY;


    @Test
    void createPayment_ShouldReturnPaymentAndSaveDto() {
        //given
        PaymentEntity paymentEntity = PaymentEntity.builder()
            .referencialOrderType(OrderType.DEPOSIT)
            .referentialOrderId(REFERENCIAL_ORDER_ID)
            .userId(USER_ID)
            .paykey(PAY_KEY)
            .amount(DEPOSIT_AMOUNT)
            .paymentStatus(PaymentStatus.PAY_IN_PROGRESS)
            .pgMethod(pgMethod)
            .build();

        when(paymentRepository.save(any(PaymentEntity.class))).thenReturn(
            paymentEntity);

        //when

        PaymentDto result = paymentService.createPaymentAndSave(REFERENCIAL_ORDER_ID,
            USER_ID, PAY_KEY, pgMethod);

        //then
        assertEquals(PaymentStatus.PAY_IN_PROGRESS, result.getPaymentStatus());
        assertEquals(REFERENCIAL_ORDER_ID, result.getReferentialOrderId());
        assertEquals(USER_ID, result.getUserId());
        assertEquals(DEPOSIT_AMOUNT, result.getAmount());
        assertEquals(pgMethod, result.getPgMethod());
        verify(paymentRepository, times(1)).save(any(PaymentEntity.class));

    }

    @Test
    void changeStatusToCompleteByTid() {
    }

    @Test
    void changeStatusToFailByTid() {
    }

    @Test
    void changeStatusToRefundedByOrderId() {
    }
}