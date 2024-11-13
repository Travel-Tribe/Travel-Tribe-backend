package com.zerobase.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class ProfileRequestDTO {

    private String introduction;  // 자기소개는 필수가 아닌 경우 그대로 둠
    @NotBlank(message = "USER-ERROR-VALID-00015")
    private String mbti;
    @NotBlank(message = "USER-ERROR-VALID-00016")
    private String smoking;
    @NotBlank(message = "USER-ERROR-VALID-00017")
    private String gender;
    @NotNull(message = "USER-ERROR-VALID-00018")
    private LocalDate birth;
    private String fileAddress;  // 파일 주소는 필수가 아닌 경우 그대로 둠
    private List<String> visitedCountries;
    private List<String> langAbilities;
}
