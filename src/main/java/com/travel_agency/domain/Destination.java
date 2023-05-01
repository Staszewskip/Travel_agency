package com.travel_agency.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long destinationId;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String postcode;

    public Destination(String country, String city, String postcode) {
        this.country = country;
        this.city = city;
        this.postcode = postcode;
    }
}
