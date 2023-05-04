package com.travel_agency.repository;

import com.travel_agency.domain.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findAll();

    @Query
    List<Reservation> findByUser(@Param("firstname") String firstname, @Param("lastname") String lastname);

}
