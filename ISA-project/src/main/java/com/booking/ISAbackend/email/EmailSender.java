package com.booking.ISAbackend.email;

import com.booking.ISAbackend.confirmationToken.ConfirmationToken;
import org.springframework.mail.MailException;

public interface EmailSender {
    void sendConfirmationAsync(String email, String token) throws InterruptedException;
    void sendConfirmationRegistrationRequest(String email) throws MailException, InterruptedException;
    void sendRejectionRegistrationRequest(String email, String message) throws MailException, InterruptedException;
}
