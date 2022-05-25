package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.confirmationToken.ConfirmationTokenService;
import com.booking.ISAbackend.dto.ClientDTO;
import com.booking.ISAbackend.dto.ClientRequest;
import com.booking.ISAbackend.email.EmailSender;
import com.booking.ISAbackend.exceptions.AccountDeletionException;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.*;
import com.booking.ISAbackend.service.ClientCategoryService;
import com.booking.ISAbackend.service.ClientService;
import com.booking.ISAbackend.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private DeleteRequestRepository deleteRequestRepository;

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

    @Autowired
    private ClientCategoryRepository clientCategoryRepository;

    @Autowired
    private ClientCategoryService clientCategoryService;

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
        c.setPoints(0);
        c.setEmailVerified(false);
        c.setPenal(0);
        c.setRole(roleRepository.findByName("CLIENT").get(0));
        clientRepository.save(c);

        String token = UUID.randomUUID().toString();
        confirmationTokenService.createVerificationToken(c, token);
        emailSender.sendConfirmationAsync(cr.getEmail(), token);

        return token;
    }

    @Override
    @Transactional
    public ClientDTO findByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        ClientCategory category = clientCategoryService.findCategoryByReservationPoints(client.getPoints()).get(0);
        if(client == null) return null;
        ClientDTO dto = new ClientDTO(
                client.getEmail(),
                client.getFirstName(),
                client.getLastName(),
                client.getPhoneNumber(),
                client.getAddress().getStreet(),
                client.getAddress().getCity(),
                client.getAddress().getState(),
                category.getName(),
                client.getPenal(),
                client.getPoints()
        );
        return dto;
    }

    @Override
    @Transactional
    public void updateInfo(String email, ClientDTO dto) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException {
        Client c = clientRepository.findByEmail(email);
        Address address = c.getAddress();
        if(!dto.getFirstName().equals("")){
            if(Validator.onlyLetterAndSpacesValidation(dto.getFirstName())) {
                c.setFirstName(dto.getFirstName());
            }
        }
        if(!dto.getLastName().equals("")){
            if(Validator.onlyLetterAndSpacesValidation(dto.getLastName())){
                c.setLastName(dto.getLastName());
            }
        }
        if(!dto.getPhoneNumber().equals("")){
            if(Validator.phoneNumberValidation(dto.getPhoneNumber())){
                c.setPhoneNumber(dto.getPhoneNumber());
            }
        }
        if(!dto.getStreet().equals("")){
            if(Validator.isValidAdress(dto.getStreet(), address.getCity(), address.getState()))
            {
                address.setStreet(dto.getStreet());
            }
        }
        if(!dto.getCity().equals("")){
            if(Validator.isValidAdress(address.getStreet(), dto.getCity(), address.getState()))
            {
                address.setCity(dto.getCity());
            }
        }
        if(!dto.getState().equals("")){
            if(Validator.isValidAdress(address.getStreet(), address.getCity(), dto.getState())) {
                address.setState(dto.getState());
            }
        }
    }

    @Override
    public void requestAccountDeletion(String email, String reason) throws AccountDeletionException {
        MyUser user = clientRepository.findByEmail(email);
        List<Reservation> reservations = reservationRepository.findClientsUpcomingReservations(user.getId());
//        Optional<DeleteRequest> request = Optional.ofNullable(deleteRequestRepository.alreadyExists(user.getId()));
        if(reservations.isEmpty()){
            deleteRequestRepository.save(new DeleteRequest(reason, user));
        }else{
            throw new AccountDeletionException("Account cannot be deleted.");
        }
    }

    @Override
    public boolean alreadyRequestedDeletion(String email) {
        MyUser user = clientRepository.findByEmail(email);
        Optional<DeleteRequest> request = Optional.ofNullable(deleteRequestRepository.alreadyExists(user.getId()));
        return request.isPresent();
    }

    @Override
    public void removeSubscribedClients(List<Client> services){
        Iterator<Client> iterator = services.iterator();
        while(iterator.hasNext()){
            iterator.remove();

        }
    }

    @Override
    public Boolean canReserve(String email){
        Integer penalties = clientRepository.getPenalties(email);
        return penalties < 3;
    }
}
