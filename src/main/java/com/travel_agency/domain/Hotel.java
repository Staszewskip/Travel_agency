package com.travel_agency.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    @Column(unique = true)
    @NotNull
    private String name;

    @ToString.Exclude
    @ManyToOne()
    @JoinColumn(name = "destinationId")
    private Destination destination;

    @OneToMany(targetEntity = Reservation.class,
            mappedBy = "hotel",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
   List<Reservation> reservationList = new ArrayList<>();

    private long unitPrice;

    public Hotel(String name,Destination destination,long unitPrice) {
        this.name = name;
        this.destination = destination;
        this.unitPrice = unitPrice;
    }
}
