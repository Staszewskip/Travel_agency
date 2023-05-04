package com.travel_agency.domain.dto.get;

import java.util.List;

public record DestinationDTOGet(String country, String city, String postcode, List<HotelDTOGet> hotelsList) {
}
