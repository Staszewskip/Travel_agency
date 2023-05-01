package com.travel_agency.repository;

import com.travel_agency.domain.Destination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends CrudRepository<Destination, Long> {
    List<Destination> findAll();
}
