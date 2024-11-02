package com.zerobase.model.type;

import com.zerobase.model.DepositInitialDto;
import com.zerobase.model.PayApiDto;
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
public class ResponseDepositPayDto {

    private Long depositId;
    private Long postId;
    private Long participationId;
    private String userId;
    private Integer amount;
    private PaymentMethod paymentMethod;
    private DepositStatus depositStatus;
    private String next_redirect_pc_url;

    public static ResponseDepositPayDto fromDepositInitialDtoAndpayApiDto
        (DepositInitialDto depositInitialDto, PayApiDto payApiDto) {
        return ResponseDepositPayDto.builder()
            .depositId(depositInitialDto.getDepositId())
            .postId(depositInitialDto.getPostId())
            .participationId(depositInitialDto.getParticipationId())
            .userId(depositInitialDto.getUserId())
            .amount(depositInitialDto.getAmount())
            .paymentMethod(depositInitialDto.getPaymentMethod())
            .depositStatus(depositInitialDto.getDepositStatus())
            .next_redirect_pc_url(payApiDto.getNext_redirect_pc_url())
            .build();

    }
}
