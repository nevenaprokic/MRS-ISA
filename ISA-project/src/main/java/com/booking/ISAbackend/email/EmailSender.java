package com.booking.ISAbackend.email;

import com.booking.ISAbackend.confirmationToken.ConfirmationToken;

public interface EmailSender {
    void sendConfirmationAsync(String email, String token) throws InterruptedException;
}
