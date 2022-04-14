package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.AdventureDTO;
import com.booking.ISAbackend.exceptions.*;

public interface AdventureService {
    void addAdventure(AdventureDTO adventure) throws AdventureAlreadyExistsException, InvalidPriceException, InvalidPeopleNumberException, RequiredFiledException, InvalidAddressException;
}
