package com.booking.ISAbackend.client;

import com.booking.ISAbackend.confirmationToken.ConfirmationTokenService;
import com.booking.ISAbackend.email.EmailSender;
import com.booking.ISAbackend.model.ClientCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private EmailSender emailSender;

    @Override
    public String save(ClientRequest cr) throws InterruptedException {
        Client c = new Client();
        c.setEmail(cr.getEmail());
        c.setPassword(passwordEncoder.encode(cr.getPassword()));
        c.setFirstName(cr.getFirstName());
        c.setLastName(cr.getLastName());
        //c.setAddress(new Address(cr.getStreet(), cr.getCity(), cr.getState()));
        c.setPhoneNumber(cr.getPhoneNumber());
        c.setDeleted(false);
        c.setClientCategory(ClientCategory.BEST_CLIENT);
        c.setEmailVerified(false);

        // u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
//        List<Role> role = roleService.findByName("CLIENT");
//        u.setRoles(roles);
        clientRepository.save(c);

        String token = UUID.randomUUID().toString();

        confirmationTokenService.createVerificationToken(c, token);
        // TODO: send email

        emailSender.sendConfirmationAsync(cr.getEmail(), token);

        return token;
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
}
