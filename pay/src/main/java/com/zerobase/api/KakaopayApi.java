package com.zerobase.api;



import com.zerobase.config.Constants;
import com.zerobase.model.RequestApi;
import com.zerobase.model.RequestApi.ConfirmDto;
import com.zerobase.model.RequestApi.RefundDto;
import com.zerobase.model.ResponseApi;
import com.zerobase.model.ResponseApi.PayRefundApiDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
@Slf4j
public class KakaopayApi implements ApiInterface {

    private final Constants constants;






    @Override
    public ResponseApi.PayReadyApiDto sendPayReadySign( Long depositId, String userId) {
        log.info("KakaopayApiService sendPayReadySign");
        // webclient url, header입력

        WebClient webclient = WebClient.builder()
            .baseUrl("https://open-api.kakaopay.com")
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("Authorization", "SECRET_KEY "+ constants.SECRET_KEY)
            .build();

        RequestApi.ReadyDto readyApiDto = RequestApi.ReadyDto.builder()
            .cid(constants.KAKAO_CID)
            .partnerOrderId(String.valueOf(depositId))
            .partnerUserId(userId)
            .itemName(constants.ITEM_NAME)
            .quantity(String.valueOf(constants.QUANTITY))
            .totalAmount(String.valueOf(constants.DEPOSIT_AMOUNT))
            .taxFreeAmount(String.valueOf(constants.TAX_FREE_AMOUNT))
            .approvalUrl(constants.KAKAO_APPROVAL_URL+"?depositId="+depositId)
            .cancelUrl(constants.KAKAO_CANCEL_URL+"?depositId="+depositId)
            .failUrl(constants.KAKAO_FAIL_URL+"?depositId="+depositId)
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



    public ResponseApi.PayConfirmDto sendPayConfirmSign(
        String tid, Long orderId, String userId, String pgToken) {
        log.info("KakaopayApiService payconfirmsign");
        // webclient url, header입력

        WebClient webclient = WebClient.builder()
            .baseUrl("https://open-api.kakaopay.com")
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("Authorization", "SECRET_KEY " + constants.SECRET_KEY)
            .build();

        ConfirmDto confirmDto = ConfirmDto.builder()
            .cid(constants.KAKAO_CID)
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
            .defaultHeader("Authorization", "SECRET_KEY " + constants.SECRET_KEY)
            .build();

        RequestApi.RefundDto refundDto = RefundDto.builder()
            .cid(constants.KAKAO_CID)
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



}



