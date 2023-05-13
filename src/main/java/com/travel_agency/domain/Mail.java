package com.travel_agency.domain;

public record Mail(String mailTo, String subject, String message) {
    public Mail(String mailTo, String subject, String message) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
    }
}
