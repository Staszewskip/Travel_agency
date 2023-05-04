package com.travel_agency.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Originator {
    private String description;
    private String name;
    private String url;
}