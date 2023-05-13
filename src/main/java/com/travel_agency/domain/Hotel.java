package com.travel_agency.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "destinationId")
    private Destination destination;

    @OneToMany(targetEntity = Reservation.class,
            mappedBy = "hotel",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonBackReference
    List<Reservation> reservationList = new ArrayList<>();

    @NotNull
    private long unitPrice;

    public Hotel(String name, Destination destination, long unitPrice) {
        this.name = name;
        this.destination = destination;
        this.unitPrice = unitPrice;
    }
}
