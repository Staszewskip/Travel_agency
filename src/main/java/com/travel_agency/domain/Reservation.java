package com.travel_agency.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Tourist reservationOwner;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "reservationList")
    private List<Tourist> touristList = new ArrayList<>();

    @NotNull
    private LocalDate checkIn_date;

    private LocalDate checkOut_date;

    @Enumerated(EnumType.ORDINAL)
    private AccomodationType accomodationType;

    public Reservation(Tourist reservationOwner, LocalDate checkIn_date, LocalDate checkOut_date, AccomodationType accomodationType) {
        this.reservationOwner = reservationOwner;
        this.checkIn_date = checkIn_date;
        this.checkOut_date = checkOut_date;
        this.accomodationType = accomodationType;
    }
}
