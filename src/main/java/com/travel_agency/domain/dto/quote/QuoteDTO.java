package com.travel_agency.domain.dto.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteDTO {
    private Originator originator;

    @JsonProperty("tags")
    private Tag[] tags;
}
