package com.travel_agency.scheduler;

import com.travel_agency.config.AdminConfig;
import com.travel_agency.domain.Mail;
import com.travel_agency.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {
    private static final String SUBJECT = "Reservations: Once a day email";
    private final SimpleEmailService simpleEmailService;
    private final ReservationRepository reservationRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = reservationRepository.count();
        String nbOfReservations = reservationRepository.count() == 1 ? "reservation" : "reservations";
        simpleEmailService.send(
                new Mail(adminConfig.getAdminMail(), SUBJECT, "Currently in database you got: " + size + " " + nbOfReservations)
        );
    }
}
