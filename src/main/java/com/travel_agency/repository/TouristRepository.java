package com.travel_agency.repository;

import com.travel_agency.domain.Tourist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TouristRepository extends CrudRepository<Tourist,Long> {
    List<Tourist> findAll();
    Optional<Tourist> findByLogin(String login);
    boolean existsByLogin(String login);
}
