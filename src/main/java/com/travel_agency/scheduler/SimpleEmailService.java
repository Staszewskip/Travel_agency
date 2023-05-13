package com.travel_agency.scheduler;

import com.travel_agency.domain.Mail;
import com.travel_agency.logger.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleEmailService {
private final JavaMailSender javaMailSender;
    private Logger logger = Logger.INSTANCE;

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.mailTo());
        mailMessage.setSubject(mail.subject());
        mailMessage.setText(mail.message());
        return mailMessage;
    }

    public void send(final Mail mail) {
        logger.log("Starting email preparation...");
        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
            logger.log("Email has been sent.");
        } catch (MailException e) {
            logger.log("Failed to process email sending: " + e.getMessage());
        }
    }
}
