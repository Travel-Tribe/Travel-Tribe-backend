package com.zerobase.travel.post.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DayDTO {
    @NotEmpty(message = "ERROR-POST-00003")
    private List<DayDetailDTO> dayDetails;

    @NotEmpty(message = "ERROR-POST-00004")
    private List<ItineraryVisitDTO> itineraryVisits;
}
