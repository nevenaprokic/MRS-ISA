package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.OwnerRegistrationRequestDTO;
import com.booking.ISAbackend.exceptions.*;

import java.util.List;

public interface RegistrationRequestService {
    boolean save(OwnerRegistrationRequestDTO request) throws InvalidAddressException, InvalidEmail, InvalidCredential, InvalidPhoneNumber, InvalidPasswordException;
    List<OwnerRegistrationRequestDTO> getAll();
}
