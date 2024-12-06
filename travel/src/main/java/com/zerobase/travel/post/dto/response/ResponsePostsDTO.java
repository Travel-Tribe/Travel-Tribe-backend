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
public class ResponsePostsDTO {

    private Long postId;
    private Long userId;
    private String title;
    private LocalDate travelStartDate;
    private LocalDate travelEndDate;
    private Integer maxParticipants;
    private String travelCountry;
    private String continent;
    private String region;
    private Integer accommodationFee;
    private Integer airplaneFee;
    private Integer otherExpenses;
    private Integer limitMaxAge;
    private Integer limitMinAge;
    private String limitSex;
    private String limitSmoke;
    private String status;
    private String mbti;
    private LocalDate deadline;
    private List<DayDTO> days;
}
