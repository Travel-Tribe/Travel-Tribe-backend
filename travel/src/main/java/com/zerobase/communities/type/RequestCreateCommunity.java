package com.zerobase.communities.type;

import com.zerobase.typeCommon.Continent;
import com.zerobase.typeCommon.Country;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateCommunity {

    @NotBlank
    Continent continent;
    @NotBlank
    Country country;
    @NotBlank
    String region;
    @NotBlank
    String title;
    @NotBlank
    String content;
    List<String> files;
}
