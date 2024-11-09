package com.zerobase.travel.post.constants;

import com.zerobase.travel.typeCommon.Country;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RepresentativeCountries {
    public static final Set<Country> ASIA = Set.of(
        Country.JP, Country.KR, Country.TH, Country.CN, Country.VN, Country.ID
    );

    public static final Set<Country> EUROPE = Set.of(
        Country.FR, Country.IT, Country.ES, Country.GB, Country.GR, Country.CH
    );

    public static final Set<Country> AFRICA = Set.of(
        Country.ZA, Country.MA, Country.EG, Country.KE, Country.TZ, Country.ET
    );

    public static final Set<Country> NORTH_AMERICA = Set.of(
        Country.US, Country.CA, Country.MX, Country.CU, Country.CR, Country.DO
    );

    public static final Set<Country> SOUTH_AMERICA = Set.of(
        Country.BR, Country.AR, Country.CL, Country.PE, Country.CO, Country.EC
    );

    public static final Set<Country> OCEANIA = Set.of(
        Country.AU, Country.NZ, Country.FJ, Country.PW, Country.WS, Country.PG
    );

    // 전체 대표 국가를 하나의 Set으로 합치기
    public static final Set<Country> ALL_REPRESENTATIVE_COUNTRIES = Set.copyOf(
        Set.of(ASIA, EUROPE, AFRICA, NORTH_AMERICA, SOUTH_AMERICA, OCEANIA)
            .stream()
            .flatMap(Set::stream)
            .toList()
    );
}