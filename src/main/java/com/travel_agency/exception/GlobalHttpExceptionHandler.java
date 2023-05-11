package com.travel_agency.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DestinationNotFoundException.class)
    public ResponseEntity<Object> handleDestinationNotFoundException(DestinationNotFoundException destinationNotFoundException) {
        return new ResponseEntity<>("Destination with given id not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<Object> handleHotelNotFoundException(HotelNotFoundException hotelNotFoundException) {
        return new ResponseEntity<>("Hotel with given id not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Object> handleReservationNotFoundException(ReservationNotFoundException reservationNotFoundException) {
        return new ResponseEntity<>("Reservation with given id not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TouristNotFoundException.class)
    public ResponseEntity<Object> handleTouristNotFoundException(TouristNotFoundException touristNotFoundException) {
        return new ResponseEntity<>("Tourist with given id not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<Object> handleLocationNotFoundException(LocationNotFoundException locationNotFoundException) {
        return new ResponseEntity<>("Location not found", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<Object> handleCurrencyNotFoundException(CurrencyNotFoundException currencyNotFoundException) {
        return new ResponseEntity<>("Currency not found", HttpStatus.BAD_REQUEST);
    }
}
