package com.booking.ISAbackend.email;

import com.booking.ISAbackend.confirmationToken.ConfirmationToken;
import com.booking.ISAbackend.dto.ReservationParamsDTO;
import org.springframework.mail.MailException;

public interface EmailSender {
    void sendConfirmationAsync(String email, String token) throws InterruptedException;
    void sendConfirmationRegistrationRequest(String email) throws MailException, InterruptedException;
    void sendRejectionRegistrationRequest(String email, String message) throws MailException, InterruptedException;
    void reservationConfirmation(ReservationParamsDTO params) throws MailException;
    void notifySubscribersNewQuickReservation(String email, String offerName, String date);
}
