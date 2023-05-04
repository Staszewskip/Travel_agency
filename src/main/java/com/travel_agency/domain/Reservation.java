package com.travel_agency.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "Reservation.findByUser",
            query = "FROM Reservation WHERE reservationOwner.firstname=:firstname AND reservationOwner.lastname=:lastname ")
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
    private List<TouristGuest> touristGuestsList = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @NotNull
    private LocalDate checkIn_date;

    private LocalDate checkOut_date;

    @Enumerated()
    private AccomodationType accomodationType;

    @Transient
    private BigDecimal totalPrice;

    private BigDecimal getTotalPrice() {
        if (totalPrice == null) {
            totalPrice = calculatePrice(checkIn_date, checkOut_date, touristGuestsList);
        }
        return totalPrice;
    }

    public BigDecimal calculatePrice(LocalDate checkIn_date, LocalDate checkOut_date, List<TouristGuest> touristGuestsList) {
        int numberOfDays = Period.between(checkIn_date, checkOut_date).getDays();
        int singleBedPrice = AccomodationType.INSTANCE.getSingleBedPrice();

        BigDecimal totalPrice = BigDecimal.valueOf(numberOfDays * (touristGuestsList.size() + 1) * singleBedPrice);
        return totalPrice;
    }

    public Reservation(Tourist reservationOwner,Hotel hotel, LocalDate checkIn_date, LocalDate checkOut_date, AccomodationType accomodationType) {
        this.reservationOwner = reservationOwner;
        this.hotel = hotel;
        this.checkIn_date = checkIn_date;
        this.checkOut_date = checkOut_date;
        this.accomodationType = accomodationType;
    }
}
