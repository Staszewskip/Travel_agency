package com.travel_agency.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Formula;

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

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "tourist_id")
    private Tourist reservationOwner;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "reservationList")
    private List<TouristGuest> touristGuestsList = new ArrayList<>();


    @ManyToOne()
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @NotNull
    private LocalDate checkIn_date;

    @NotNull
    private LocalDate checkOut_date;

    @Transient
    private Long totalPrice;

    @PostLoad
    public void setTotalPrice() {
        totalPrice = getTotalPrice(checkIn_date,checkOut_date, touristGuestsList);
    }
    public long getTotalPrice(LocalDate checkIn_date, LocalDate checkOut_date, List<TouristGuest> touristGuestsList) {
        long numberOfDays = Period.between(checkIn_date, checkOut_date).getDays();
        long unitPrice = hotel.getUnitPrice();
        long numberOfGuests = touristGuestsList.size() + 1;
        long totalPrice = numberOfDays * numberOfGuests * unitPrice;
        return totalPrice;
    }

    public Reservation(Tourist reservationOwner, Hotel hotel, LocalDate checkIn_date, LocalDate checkOut_date) {
        this.reservationOwner = reservationOwner;
        this.hotel = hotel;
        this.checkIn_date = checkIn_date;
        this.checkOut_date = checkOut_date;
        this.touristGuestsList = new ArrayList<>();
    }
}
