package com.travel_agency.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @NotNull
    private String name;

    @ManyToOne()
    @JoinColumn(name = "destinationId")
    private Destination destination;

    @OneToMany(targetEntity = Reservation.class,
            mappedBy = "reservationId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
   List<Reservation> reservationList = new ArrayList<>();

    public Hotel(String name,Destination destination) {
        this.name = name;
        this.destination = destination;
    }
}
