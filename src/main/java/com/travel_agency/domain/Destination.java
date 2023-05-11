package com.travel_agency.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long destinationId;

    @NotNull
    private String country;

    @Column
    @NotNull
    private String city;

    @NotNull
    private String postcode;

    @OneToMany(targetEntity = Hotel.class,
            mappedBy = "destination",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonBackReference
    List<Hotel> hotelList = new ArrayList<>();

    public Destination(String country, String city, String postcode) {
        this.country = country;
        this.city = city;
        this.postcode = postcode;
    }
}
