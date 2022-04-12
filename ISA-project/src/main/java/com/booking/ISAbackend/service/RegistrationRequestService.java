package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.OwnerRegistrationRequest;

public interface RegistrationRequestService {
    boolean save(OwnerRegistrationRequest request);
}
