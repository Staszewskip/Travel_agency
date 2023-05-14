package com.travel_agency.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="tourists_guests")
public class TouristGuest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long touristGuestId;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private LocalDate birthdate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "join_touristGuest_reservation",
            joinColumns = {@JoinColumn(name = "touristGuestId", referencedColumnName = "touristGuestId")},
            inverseJoinColumns = {@JoinColumn(name = "reservationId", referencedColumnName = "reservationId")}
    )
    private List<Reservation> reservationList = new ArrayList<>();

    public TouristGuest(String firstname, String lastname, LocalDate birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.reservationList = new ArrayList<>();
    }
}
