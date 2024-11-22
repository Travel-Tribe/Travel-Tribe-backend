package com.zerobase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class RequestApi {


    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class ConfirmDto {

        private String cid;

        private String tid;


        @JsonProperty("partner_order_id")
        private String partnerOrderId;

        @JsonProperty("partner_user_id")
        private String partnerUserId;

        @JsonProperty("pg_token")
        private String pgToken;



    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class ReadyDto {
        private String cid;

        @JsonProperty("partner_order_id")
        private String partnerOrderId;

        @JsonProperty("partner_user_id")
        private String partnerUserId;

        @JsonProperty("item_name")
        private String itemName;

        @JsonProperty("quantity")
        private String quantity;

        @JsonProperty("total_amount")
        private String totalAmount;

        @JsonProperty("tax_free_amount")
        private String taxFreeAmount;

        @JsonProperty("vat_amount")
        private String vatAmount;

        @JsonProperty("approval_url")
        private  String approvalUrl;

        @JsonProperty("cancel_url")
        private  String cancelUrl;

        @JsonProperty("fail_url")
        private  String failUrl;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class RefundDto {

        private String cid;

        private String tid;

        @JsonProperty("cancel_amount")
        private String cancelAmount;

        @JsonProperty("cancel_tax_free_amount")
        private String cancelTaxFreeAmount;

        @JsonProperty("cancel_vat_amount")
        private String cancelVatAmount;







    }
}
