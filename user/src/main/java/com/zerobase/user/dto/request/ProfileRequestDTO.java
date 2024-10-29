package com.zerobase.user.dto.request;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class ProfileRequestDTO {
    private String introduction;
    private String mbti;
    private String smoking;
    private String gender;
    private LocalDate birth;
    private String fileAddress;
    private List<String> countryName;
    private List<String> lang;
}
