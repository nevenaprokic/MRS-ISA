package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.OwnerRegistrationRequest;
import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.MyUser;
import com.booking.ISAbackend.model.RegistrationRequest;
import com.booking.ISAbackend.repository.AddressRepository;
import com.booking.ISAbackend.repository.RegistrationRequestRepository;
import com.booking.ISAbackend.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationRequestServiceImpl implements RegistrationRequestService {

    @Autowired
    RegistrationRequestRepository registrationRequestRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserServiceImpl userService;

    @Override
    public boolean save(OwnerRegistrationRequest request) {
        MyUser myUser =  userService.findByEmail(request.email);
        if(myUser == null){
            Address address = new Address(request.street, request.city, request.state);
            RegistrationRequest registrationRequest = new RegistrationRequest(request.explanation,request.type,request.firstName,request.lastName, request.password, request.phoneNumber,request.email,false,address);
            addressRepository.save(address);
            registrationRequestRepository.save(registrationRequest);
            return true;
        }
        return false;

    }

}
