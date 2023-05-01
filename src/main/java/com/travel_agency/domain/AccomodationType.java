package com.travel_agency.domain;

import java.math.BigDecimal;

public enum AccomodationType {
    LOW_SEASON(new BigDecimal(100), new BigDecimal(150)),
    HIGH_SEASON(new BigDecimal(100), new BigDecimal(150));

    private final BigDecimal singleBedPrice;
    private final BigDecimal doubleBedPrice;

    AccomodationType(BigDecimal singleBedPrice, BigDecimal doubleBedPrice) {
        this.singleBedPrice = singleBedPrice;
        this.doubleBedPrice = doubleBedPrice;
    }
}
