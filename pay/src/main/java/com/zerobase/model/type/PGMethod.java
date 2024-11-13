package com.zerobase.model.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PGMethod {
    @JsonProperty("KAKAOPAY")
    KAKAOPAY("카카오페이");

    private final String korean;


}
