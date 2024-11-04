package com.zerobase.travel.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DayDetailDTO {
    @NotBlank(message = "일차 상세 제목은 필수입니다.")
    private String title;

    @NotBlank(message = "일차 상세 설명은 필수입니다.")
    private String description;

    private String fileAddress; // 파일 주소는 선택 사항일 수 있습니다.
}