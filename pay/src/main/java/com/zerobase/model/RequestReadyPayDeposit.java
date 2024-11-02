package com.zerobase.model;

import com.zerobase.model.type.PaymentMethod;
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
public class RequestReadyPayDeposit {
    Long postId;
    Long participationId;
    String userId;
    PaymentMethod paymentMethod;
}
