package com.travel_agency.repository;

import com.travel_agency.domain.TouristGuest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristGuestRepository extends CrudRepository<TouristGuest,Long> {
}
