package com.zerobase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestPayAPiDto {
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

    @JsonProperty("approval_url")
    private  String approvalUrl;

    @JsonProperty("cancel_url")
    private  String cancelUrl;

    @JsonProperty("fail_url")
    private  String failUrl;
}
