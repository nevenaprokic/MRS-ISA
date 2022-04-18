package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.OwnerRegistrationRequest;
import com.booking.ISAbackend.exceptions.*;

public interface RegistrationRequestService {
    boolean save(OwnerRegistrationRequest request) throws InvalidAddressException, InvalidEmail, InvalidCredential, InvalidPhoneNumber, InvalidPasswordException;
}
