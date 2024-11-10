package com.zerobase.travel.post.dto.response;

import com.zerobase.travel.post.dto.request.DayDTO;
import com.zerobase.travel.post.type.LimitSex;
import com.zerobase.travel.post.type.LimitSmoke;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponsePostDTO {

    private Long userId;
    private String title;
    private LocalDate travelStartDate;
    private LocalDate travelEndDate;
    private Integer maxParticipants;
    private Country travelCountry;
    private Continent continent;
    private String region;
    private Integer accommodationFee;
    private Integer airplaneFee;
    private Integer otherExpenses;
    private Integer limitMaxAge;
    private Integer limitMinAge;
    private LimitSex limitSex;
    private LimitSmoke limitSmoke;
    private LocalDate deadline;
    private List<DayDTO> days;
}
