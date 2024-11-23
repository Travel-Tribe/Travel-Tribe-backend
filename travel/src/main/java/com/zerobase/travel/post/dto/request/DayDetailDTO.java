package com.zerobase.travel.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DayDetailDTO {
    @NotBlank(message = "ERROR-POST-00001")
    private String title;

    @NotBlank(message = "ERROR-POST-00002")
    private String description;

    private String fileAddress; // 파일 주소는 선택 사항일 수 있습니다.
}