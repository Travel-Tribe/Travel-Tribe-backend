package com.zerobase.travel.communities.type;

import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
    List<String> files = new ArrayList<>();



}




