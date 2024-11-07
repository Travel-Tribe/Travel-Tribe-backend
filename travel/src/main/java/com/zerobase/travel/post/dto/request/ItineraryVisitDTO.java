package com.zerobase.travel.post.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItineraryVisitDTO {
    @NotNull(message = "위치 정보는 필수입니다.")
    private Double latitude;

    @NotNull(message = "위치 정보는 필수입니다.")
    private Double longitude;

    @NotNull(message = "순서는 필수입니다.")
    private Integer orderNumber;
}