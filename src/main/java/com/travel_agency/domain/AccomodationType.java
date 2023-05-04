package com.travel_agency.domain;

import lombok.Getter;

import java.math.BigDecimal;
@Getter
public enum AccomodationType {
    INSTANCE(100,150);

    private final int singleBedPrice;
    private final int doubleBedPrice;

    AccomodationType(int singleBedPrice, int doubleBedPrice) {
        this.singleBedPrice = singleBedPrice;
        this.doubleBedPrice = doubleBedPrice;
    }
}
