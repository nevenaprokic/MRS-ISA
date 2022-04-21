package com.booking.ISAbackend.client;

import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;

public interface ClientService {

    String save(ClientRequest c) throws InterruptedException;
    Client findByEmail(String email);

    void updateInfo(String email, ClientDTO dto) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException;
}
