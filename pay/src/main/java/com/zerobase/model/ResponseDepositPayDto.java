package com.zerobase.model;

import static com.zerobase.config.Constants.DEPOSIT_AMOUNT;

import com.zerobase.model.ResponseApi.PayReadyApiDto;
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

    private Long postId;
    private Long participationId;
    private Long depositId;
    private Long amount;
    private String userId;
    private String next_redirect_pc_url;


    public static ResponseDepositPayDto from
        (DepositDto depositDto, PayReadyApiDto payReadyApiDto) {
        return ResponseDepositPayDto.builder()
            .depositId(depositDto.getDepositId())
            .postId(depositDto.getPostId())
            .participationId(depositDto.getParticipationId())
            .userId(depositDto.getUserId())
            .amount(DEPOSIT_AMOUNT)
            .next_redirect_pc_url(payReadyApiDto.getNext_redirect_pc_url())
            .build();


    }
}
