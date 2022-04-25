package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.OwnerRegistrationRequestDTO;
import com.booking.ISAbackend.exceptions.*;

public interface RegistrationRequestService {
    boolean save(OwnerRegistrationRequestDTO request) throws InvalidAddressException, InvalidEmail, InvalidCredential, InvalidPhoneNumber, InvalidPasswordException;
}
