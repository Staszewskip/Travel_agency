package com.travel_agency.repository;

import com.travel_agency.domain.Destination;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface DestinationRepository extends CrudRepository<Destination, Long> {
    List<Destination> findAll();
}
