package com.zerobase.travel.dto.request;

import com.zerobase.travel.type.Continent;
import com.zerobase.travel.type.Country;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPostCommunity {


    @Min(1)
    long communityId;
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
