package com.zerobase.service;

import static com.zerobase.config.Constants.DEPOSIT_AMOUNT;
import static com.zerobase.config.Constants.ITEM_NAME;
import static com.zerobase.config.Constants.QUANTITY;
import static com.zerobase.config.Constants.TAX_FREE_AMOUNT;

import com.zerobase.model.PayApiDto;
import com.zerobase.model.RequestPayAPiDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
@Slf4j
public class KakaopayApiService implements ApiService {

    @Value("${paymentMethod.kakaopay.secretkey-dev}")
    private String SECRET_KEY;

    private static final String MOCK_CID = "TC0ONETIME";



    @Override
    public PayApiDto getReady(Long orderId, String userId) {
        log.info("KakaopayApiService getReady");
        // webclient url, header입력
        WebClient webclient = WebClient.builder()
            .baseUrl("https://open-api.kakaopay.com")
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("Authorization", "SECRET_KEY "+ SECRET_KEY)
            .build();

        RequestPayAPiDto requestPayApiDto = RequestPayAPiDto.builder()
            .cid(MOCK_CID)
            .partnerOrderId(String.valueOf(orderId))
            .partnerUserId(userId)
            .itemName(ITEM_NAME)
            .quantity(String.valueOf(QUANTITY))
            .totalAmount(String.valueOf(DEPOSIT_AMOUNT))
            .taxFreeAmount(String.valueOf(TAX_FREE_AMOUNT))
            .approvalUrl(PayManagmentService.APPROVAL_URL)
            .cancelUrl(PayManagmentService.CANCEL_URL)
            .failUrl(PayManagmentService.FAIL_URL)
            .build();
        //
        PayApiDto payApiDto = webclient.post()
            .uri("/online/v1/payment/ready")
            .bodyValue(
                requestPayApiDto
            )
            .retrieve()
            .bodyToMono(PayApiDto.class)
            .block();

        return payApiDto;
    }
}



