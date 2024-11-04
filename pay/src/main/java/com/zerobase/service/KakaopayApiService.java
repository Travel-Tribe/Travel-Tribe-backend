package com.zerobase.service;

import static com.zerobase.config.Constants.DEPOSIT_AMOUNT;
import static com.zerobase.config.Constants.ITEM_NAME;
import static com.zerobase.config.Constants.KAKAO_APPROVAL_URL;
import static com.zerobase.config.Constants.KAKAO_CANCEL_URL;
import static com.zerobase.config.Constants.KAKAO_CID;
import static com.zerobase.config.Constants.KAKAO_FAIL_URL;
import static com.zerobase.config.Constants.QUANTITY;
import static com.zerobase.config.Constants.SECRET_KEY;
import static com.zerobase.config.Constants.TAX_FREE_AMOUNT;

import com.zerobase.model.ResponseApi;
import com.zerobase.model.ResponseApi.PayRefundApiDto;
import com.zerobase.model.RequestApi;
import com.zerobase.model.RequestApi.ConfirmDto;
import com.zerobase.model.RequestApi.RefundDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
@Slf4j
public class KakaopayApiService implements ApiService {






    @Override
    public ResponseApi.PayReadyApiDto sendPayReadySign( Long orderId, String userId) {
        log.info("KakaopayApiService sendPayReadySign");
        // webclient url, header입력

        WebClient webclient = WebClient.builder()
            .baseUrl("https://open-api.kakaopay.com")
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("Authorization", "SECRET_KEY "+ SECRET_KEY)
            .build();

        RequestApi.ReadyDto readyApiDto = RequestApi.ReadyDto.builder()
            .cid(KAKAO_CID)
            .partnerOrderId(String.valueOf(orderId))
            .partnerUserId(userId)
            .itemName(ITEM_NAME)
            .quantity(String.valueOf(QUANTITY))
            .totalAmount(String.valueOf(DEPOSIT_AMOUNT))
            .taxFreeAmount(String.valueOf(TAX_FREE_AMOUNT))
            .approvalUrl(KAKAO_APPROVAL_URL)
            .cancelUrl(KAKAO_CANCEL_URL)
            .failUrl(KAKAO_FAIL_URL)
            .build();
        //
        ResponseApi.PayReadyApiDto payReadyApiDto = webclient.post()
            .uri("/online/v1/payment/ready")
            .bodyValue(
                readyApiDto
            )
            .retrieve()
            .bodyToMono(ResponseApi.PayReadyApiDto.class)
            .block();

        return payReadyApiDto;
    }

    /*

    public ResponseApi.PayConfirmDto sendPayConfirmSign(
        String tid, Long orderId, String userId, String pgToken) {
        log.info("KakaopayApiService payconfirmsign");
        // webclient url, header입력

        WebClient webclient = WebClient.builder()
            .baseUrl("https://open-api.kakaopay.com")
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("Authorization", "SECRET_KEY " + SECRET_KEY)
            .build();

        ConfirmDto confirmDto = ConfirmDto.builder()
            .cid(KAKAO_CID)
            .tid(tid)
            .partnerOrderId(String.valueOf(orderId))
            .partnerUserId(userId)
            .pgToken(pgToken)
            .build();
        //
        ResponseApi.PayConfirmDto payConfirmDto = webclient.post()
            .uri("/online/v1/payment/approve")
            .bodyValue(
                confirmDto
            )
            .retrieve()
            .bodyToMono(ResponseApi.PayConfirmDto.class)
            .block();

        return payConfirmDto;
    }

    public PayRefundApiDto sendPayRefundSign(
         String tid, Long cancelAmount, Long cancel_tax_free_amount ) {
        log.info("KakaopayApiService payRefundSign");
        // webclient url, header입력

        WebClient webclient = WebClient.builder()
            .baseUrl("https://open-api.kakaopay.com")
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("Authorization", "SECRET_KEY " + SECRET_KEY)
            .build();

        RequestApi.RefundDto refundDto = RefundDto.builder()
            .cid(KAKAO_CID)
            .tid(tid)
            .cancelAmount(String.valueOf(cancelAmount))
            .cancelTaxFreeAmount(String.valueOf(cancel_tax_free_amount))
            .build();
        //
        PayRefundApiDto payRefundApiDto = webclient.post()
            .uri("/online/v1/payment/cancel")
            .bodyValue(
                refundDto
            )
            .retrieve()
            .bodyToMono(PayRefundApiDto.class)
            .block();

        return payRefundApiDto;
    }


     */
}



