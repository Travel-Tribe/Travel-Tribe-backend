package com.zerobase.travel.typeCommon;

import lombok.AllArgsConstructor;


@AllArgsConstructor
 public enum Continent {
    AFRICA(1),
    ASIA(2),
    EUROPE(3),
    AUSTRALIA(4),
    NORTH_AMERICA(5),
    SOUTH_AMERICA(6),
    ANTARCTIC(7);

    private final int number;
}
