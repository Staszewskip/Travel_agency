package com.travel_agency.domain;

import lombok.Getter;

import java.math.BigDecimal;
@Getter
public enum AccomodationType {
    LOW_SEASON(100,150),
    HIGH_SEASON(150,200);

    private final int singleBedPrice;
    private final int doubleBedPrice;

    AccomodationType(int singleBedPrice, int doubleBedPrice) {
        this.singleBedPrice = singleBedPrice;
        this.doubleBedPrice = doubleBedPrice;
    }
}
