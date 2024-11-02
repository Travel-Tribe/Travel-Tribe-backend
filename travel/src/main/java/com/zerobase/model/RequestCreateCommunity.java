package com.zerobase.model;

import com.zerobase.typeCommon.Continent;
import com.zerobase.typeCommon.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateCommunity {

    @NotNull
    Continent continent;
    @NotNull
    Country country;
    @NotBlank
    String region;
    @NotBlank
    String title;
    @NotBlank
    String content;
    List<String> files;
}
