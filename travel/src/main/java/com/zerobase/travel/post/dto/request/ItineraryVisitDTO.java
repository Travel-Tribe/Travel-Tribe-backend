package com.zerobase.travel.post.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItineraryVisitDTO {
    @NotNull(message = "ERROR-POST-00005")
    private Double latitude;

    @NotNull(message = "ERROR-POST-00006")
    private Double longitude;

    @NotNull(message = "ERROR-POST-00007")
    private Integer orderNumber;
}