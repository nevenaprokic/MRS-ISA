package com.booking.ISAbackend.client;

import com.booking.ISAbackend.confirmationToken.ConfirmationTokenService;
import com.booking.ISAbackend.email.EmailSender;
import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.ClientCategory;
import com.booking.ISAbackend.repository.AddressRepository;
import com.booking.ISAbackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository adressRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private EmailSender emailSender;

    @Override
    @Transactional
    public String save(ClientRequest cr) throws InterruptedException {
        Client c = new Client();
        c.setEmail(cr.getEmail());
        c.setPassword(passwordEncoder.encode(cr.getPassword()));
        c.setFirstName(cr.getFirstName());
        c.setLastName(cr.getLastName());
        Address a = new Address(cr.getStreet(), cr.getCity(), cr.getState());
        adressRepository.save(a);
        c.setAddress(a);
        c.setPhoneNumber(cr.getPhoneNumber());
        c.setDeleted(false);
        c.setClientCategory(ClientCategory.BEST_CLIENT);
        c.setEmailVerified(false);
        c.setPenal(0);
        c.setRole(roleRepository.findByName("CLIENT").get(0));

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
