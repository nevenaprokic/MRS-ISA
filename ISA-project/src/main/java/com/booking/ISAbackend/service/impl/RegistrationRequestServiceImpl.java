package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.OwnerRegistrationRequestDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.MyUser;
import com.booking.ISAbackend.model.RegistrationRequest;
import com.booking.ISAbackend.repository.AddressRepository;
import com.booking.ISAbackend.repository.RegistrationRequestRepository;
import com.booking.ISAbackend.service.RegistrationRequestService;
import com.booking.ISAbackend.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationRequestServiceImpl implements RegistrationRequestService {

    @Autowired
    RegistrationRequestRepository registrationRequestRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserServiceImpl userService;

    @Override
    public boolean save(OwnerRegistrationRequestDTO request) throws InvalidAddressException, InvalidEmail, InvalidCredential, InvalidPhoneNumber, InvalidPasswordException {
        MyUser myUser =  userService.findByEmail(request.getEmail());
        if(validateRequest(request)) {
            if(myUser == null) {
                Address address = new Address(request.getStreet(), request.getCity(), request.getState());
                LocalDate sendingTime = LocalDate.now();
                RegistrationRequest registrationRequest = new RegistrationRequest(request.getExplanation(), request.getType(), request.getFirstName(), request.getLastName(), request.getPassword(), request.getPhoneNumber(), request.getEmail(), false, sendingTime,  address);
                addressRepository.save(address);
                registrationRequestRepository.save(registrationRequest);
                return true;
            }
        }
        return false;

    }

    @Override
    @Transactional
    public List<OwnerRegistrationRequestDTO> getAll() {
        List<RegistrationRequest> allRegistrationRequests = registrationRequestRepository.findAll();
        List<OwnerRegistrationRequestDTO> requestDTOS = new ArrayList<OwnerRegistrationRequestDTO>();
        for(RegistrationRequest request : allRegistrationRequests){
            Address a = request.getAddress();
            OwnerRegistrationRequestDTO registrationRequest = new OwnerRegistrationRequestDTO(request.getDescription(),
                    request.getPersonType(),
                    request.getFirstName(),
                    request.getLastName(),
                    request.getPhoneNumber(),
                    request.getEmail(),
                    a.getStreet(),
                    a.getCity(),
                    a.getState(),
                    request.getId(),
                    formatDate(request.getSendingTime()));
            requestDTOS.add(registrationRequest);

        }
        return requestDTOS;
    }

    private boolean validateRequest(OwnerRegistrationRequestDTO request) throws InvalidAddressException, InvalidPasswordException, InvalidEmail, InvalidCredential, InvalidPhoneNumber {
        boolean validationResult = Validator.isValidAdress(request.getStreet(), request.getCity(), request.getState())
                && Validator.isMachPassword(request.getPassword(), request.getConfirmPassword())
                && Validator.isValidEmail(request.getEmail())
                && Validator.isValidCredentials(request.getFirstName())
                && Validator.isValidCredentials(request.getLastName())
                && Validator.isValidPhoneNumber(request.getPhoneNumber())
                && (!request.getExplanation().isEmpty());
        return validationResult;
    }

    private String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return formatter.format(date);
    }

}
