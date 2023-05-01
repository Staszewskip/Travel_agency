package com.travel_agency.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TouristDTO {
 
    private long touristId;

    private String firstname;

    private String lastname;

    private boolean isAdult;

    private String login;

    private String password;

    private String email;

    private int phoneNumber;
}
