package com.zerobase.travel.post.dto.response;

import com.zerobase.travel.post.dto.request.DayDTO;
import com.zerobase.travel.post.type.LimitSex;
import com.zerobase.travel.post.type.LimitSmoke;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private Integer transportationFee;
    private Integer airplaneFee;
    private Integer foodFee;
    private Integer limitMaxAge;
    private Integer limitMinAge;
    private LimitSex limitSex;
    private LimitSmoke limitSmoke;
    private String preferenceType;
    private LocalDate deadline;
    private List<DayDTO> days;
}