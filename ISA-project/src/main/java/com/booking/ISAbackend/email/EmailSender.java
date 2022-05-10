package com.booking.ISAbackend.email;

import com.booking.ISAbackend.confirmationToken.ConfirmationToken;
import org.springframework.mail.MailException;

public interface EmailSender {
    void sendConfirmationAsync(String email, String token) throws InterruptedException;

}
