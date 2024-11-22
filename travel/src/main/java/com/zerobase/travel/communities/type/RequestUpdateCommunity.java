package com.zerobase.travel.communities.type;

import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestUpdateCommunity {


    @Min(1)
    private long communityId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private List<String> files=new ArrayList<>();
}
