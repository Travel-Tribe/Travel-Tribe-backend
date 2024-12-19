package com.zerobase.travel.typeCommon;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum Continent {
    AFRICA(1, "아프리카"),
    ASIA(2, "아시아"),
    EUROPE(3, "유럽"),
    OCEANIA(4, "오세아니아"),
    NORTH_AMERICA(5, "북아메리카"),
    SOUTH_AMERICA(6, "남아메리카"),
    ANTARCTIC(7, "남극");
    //
    private final int number;
    private final String name;
}
