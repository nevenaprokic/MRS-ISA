package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.OwnerRegistrationRequestDTO;
import com.booking.ISAbackend.repository.RegistrationRequestRepository;
import com.booking.ISAbackend.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("registration-request")
public class RegistrationRequestController {

    @Autowired
    private RegistrationRequestService registrationRequests;

    @GetMapping("get-all")
    public ResponseEntity<List<OwnerRegistrationRequestDTO>> getRegistrationRequests(){
        try{
            List<OwnerRegistrationRequestDTO> requestDTOS = registrationRequests.getAll();
            return ResponseEntity.ok(requestDTOS);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
