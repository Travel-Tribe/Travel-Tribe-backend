package com.zerobase.travel.post.dto.request;

import com.zerobase.travel.post.type.LimitSex;
import com.zerobase.travel.post.type.LimitSmoke;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

// 예시: PostDTO 클래스에 유효성 검사 추가
@Data
public class PostDTO {

    @NotBlank(message = "ERROR-POST-00001")
    private String title;

    @NotNull(message = "ERROR-POST-00008")
    @FutureOrPresent(message = "ERROR-POST-00009")
    private LocalDate travelStartDate;

    @NotNull(message = "ERROR-POST-00010")
    @Future(message = "ERROR-POST-00011")
    private LocalDate travelEndDate;

    @NotNull(message = "ERROR-POST-00012")
    @Min(value = 1, message = "ERROR-POST-00013")
    private Integer maxParticipants;

    @NotNull(message = "ERROR-POST-00014")
    private Country travelCountry;

    @NotNull(message = "ERROR-POST-00015")
    private Continent continent;

    @NotBlank(message = "ERROR-POST-00016")
    private String region;

    @NotNull(message = "ERROR-POST-00017")
    @Min(value = 0, message = "ERROR-POST-00018")
    private Integer accommodationFee;

    @NotNull(message = "ERROR-POST-00019")
    @Min(value = 0, message = "ERROR-POST-00020")
    private Integer otherExpenses;

    @NotNull(message = "ERROR-POST-00021")
    @Min(value = 0, message = "ERROR-POST-00022")
    private Integer airplaneFee;

    @NotNull(message = "ERROR-POST-00023")
    @Min(value = 0, message = "ERROR-POST-00024")
    private Integer limitMaxAge;

    @NotNull(message = "ERROR-POST-00025")
    @Min(value = 0, message = "ERROR-POST-00026")
    private Integer limitMinAge;

    @NotNull(message = "ERROR-POST-00027")
    private LimitSex limitSex;

    @NotNull(message = "ERROR-POST-00028")
    private LimitSmoke limitSmoke;

    @NotNull(message = "ERROR-POST-00029")
    @Future(message = "ERROR-POST-00030")
    private LocalDate deadline;

    @NotEmpty(message = "ERROR-POST-00031")
    private List<DayDTO> days;
}
