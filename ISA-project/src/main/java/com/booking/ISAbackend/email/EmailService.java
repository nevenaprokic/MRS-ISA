package com.booking.ISAbackend.email;

import com.booking.ISAbackend.confirmationToken.ConfirmationToken;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;

import java.util.Objects;

@Service
public class EmailService implements EmailSender{

    @Autowired
    private JavaMailSender javaMailSender;

    /*
     * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
     */
    @Autowired
    private Environment env;

    /*
     * Anotacija za oznacavanje asinhronog zadatka
     * Vise informacija na: https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#scheduling
     */
    @Async
    public void sendConfirmationAsync(String email, String token) throws MailException, InterruptedException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        mail.setSubject("Primer slanja emaila pomoću asinhronog Spring taska");
        String link = "http://localhost:8081/confirmation?token=" + token;
        mail.setText("Pozdrav hvala što pratiš ISA, aktiviraj svoj account na " + link + ".");
        javaMailSender.send(mail);

    }

    @Async
    public void sendConfirmationRegistrationRequest(String email) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        mail.setSubject("Response for registration request");
        mail.setText("Hello, your registration request has been accepted. You can use our application now. Good Luck!");
        javaMailSender.send(mail);

    }
    @Async
    public void sendRejectionRegistrationRequest(String email, String message) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        mail.setSubject("Response for registration request");
        mail.setText("Hello, your registration request has been denied with the following explanation.\n" + message);
        javaMailSender.send(mail);

    }

}