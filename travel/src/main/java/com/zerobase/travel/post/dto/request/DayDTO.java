package com.zerobase.travel.post.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class DayDTO {
    @NotEmpty(message = "일차 상세는 최소 1개 이상이어야 합니다.")
    private List<DayDetailDTO> dayDetails;

    @NotEmpty(message = "여행 일정 방문지는 최소 1개 이상이어야 합니다.")
    private List<ItineraryVisitDTO> itineraryVisits;
}
