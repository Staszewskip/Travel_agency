package com.travel_agency.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "tourists")
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long touristId;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private LocalDate birthdate;

    @NotNull
    private String login;

    @NotNull
    private String password;

    private String passwordHash;

    @NotNull
    private String email;

    @NotNull
    private int phoneNumber;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "join_tourist_reservation",
            joinColumns = {@JoinColumn(name = "touristId", referencedColumnName = "touristId")},
            inverseJoinColumns = {@JoinColumn(name = "reservationId", referencedColumnName = "reservationId")}
    )
    private List<Reservation> reservationList = new ArrayList<>();

    public Tourist(String firstname, String lastname, LocalDate birthdate, String login, String password,String passwordHash, String email, int phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.login = login;
        this.password = password;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
