package com.zerobase.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class ProfileRequestDTO {

    private String introduction;  // 자기소개는 필수가 아닌 경우 그대로 둠
    @NotBlank(message = "E01031")
    private String mbti;
    @NotBlank(message = "E01032")
    private String smoking;
    @NotBlank(message = "E01033")
    private String gender;
    @NotNull(message = "E01034")
    private LocalDate birth;
    private String fileAddress;  // 파일 주소는 필수가 아닌 경우 그대로 둠
    private List<String> visitedCountries;
    private List<String> langAbilities;
}
