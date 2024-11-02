package com.zerobase.service;

import com.zerobase.model.DepositInitialDto;
import com.zerobase.model.PayApiDto;
import com.zerobase.model.type.PaymentMethod;
import com.zerobase.model.type.ResponseDepositPayDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayManagmentService {

    private final DepositService depositService;
    private final KakaopayApiService kakaopayApiService;

    public static final String APPROVAL_URL = "http://localhost:8080";
    public static final String FAIL_URL = "http://localhost:8080";
    public static final String CANCEL_URL = "http://localhost:8080";




    @Transactional
    public ResponseDepositPayDto readyDepositPay(
        Long postId, Long participationId, String userId,
        PaymentMethod paymentMethod) {
        log.info("readyDepositPay");

        PayApiDto payApiDto = kakaopayApiService.getReady( participationId, userId);
        DepositInitialDto depositInitialDto = depositService.initialSave(postId,
            participationId, userId, payApiDto.getTid(), paymentMethod);

        return ResponseDepositPayDto.fromDepositInitialDtoAndpayApiDto
                (depositInitialDto, payApiDto);
    }

}
