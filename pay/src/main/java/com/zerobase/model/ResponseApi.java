package com.zerobase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zerobase.model.type.PayMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ResponseApi {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PayConfirmDto {
        private String aid;
        private String tid;
        @JsonProperty("payment_method_type")
        private PayMethod payMethod;

    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PayReadyApiDto {
        private String tid;
        @JsonProperty("next_redirect_pc_url")
        private String nextRedirectPcUrl;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PayRefundApiDto {
        private String aid;
        private String tid;
        private String status;
        @JsonProperty("approved_cancel_amount")
        private CancelAmountDto approvedCancelAmount;


        @Getter
        @Setter
        @NoArgsConstructor
        public class CancelAmountDto {

            int total;

        }
    }
}
