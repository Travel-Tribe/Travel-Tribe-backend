package com.zerobase.travel.post.dto.request;

import com.zerobase.travel.post.type.LimitSex;
import com.zerobase.travel.post.type.LimitSmoke;
import com.zerobase.travel.post.type.PostStatus;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
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

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotNull(message = "여행 시작 날짜는 필수입니다.")
    private LocalDate travelStartDate;

    @NotNull(message = "여행 종료 날짜는 필수입니다.")
    private LocalDate travelEndDate;

    @NotNull(message = "최대 참여 인원은 필수입니다.")
    @Min(value = 1, message = "최소 1명 이상이어야 합니다.")
    private Integer maxParticipants;

    @NotNull(message = "여행 국가는 필수입니다.")
    private Country travelCountry;

    @NotNull(message = "대륙은 필수입니다.")
    private Continent continent;

    @NotBlank(message = "지역은 필수입니다.")
    private String region;

    @NotNull(message = "숙박비는 필수입니다.")
    @Min(value = 0, message = "숙박비는 0 이상이어야 합니다.")
    private Integer accommodationFee;

    @NotNull(message = "교통비는 필수입니다.")
    @Min(value = 0, message = "교통비는 0 이상이어야 합니다.")
    private Integer transportationFee;

    @NotNull(message = "비행기표는 필수입니다.")
    @Min(value = 0, message = "비행기표는 0 이상이어야 합니다.")
    private Integer airplaneFee;

    @NotNull(message = "식대는 필수입니다.")
    @Min(value = 0, message = "식대는 0 이상이어야 합니다.")
    private Integer foodFee;

    @NotNull(message = "최대 나이는 필수입니다.")
    @Min(value = 0, message = "최대 나이는 0 이상이어야 합니다.")
    private Integer limitMaxAge;

    @NotNull(message = "최소 나이는 필수입니다.")
    @Min(value = 0, message = "최소 나이는 0 이상이어야 합니다.")
    private Integer limitMinAge;

    @NotNull(message = "성별 제한은 필수입니다.")
    private LimitSex limitSex;

    @NotNull(message = "흡연 여부는 필수입니다.")
    private LimitSmoke limitSmoke;

    @NotBlank(message = "선호 유형은 필수입니다.")
    private String preferenceType;

    @NotNull(message = "마감 날짜는 필수입니다.")
    private LocalDate deadline;

    @NotNull(message = "상태는 필수입니다.")
    private PostStatus status;

    @NotEmpty(message = "여행 일정은 최소 1일 이상이어야 합니다.")
    private List<DayDTO> days;
}
