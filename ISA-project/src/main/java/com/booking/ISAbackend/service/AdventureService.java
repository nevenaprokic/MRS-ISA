package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.AdventureDTO;
import com.booking.ISAbackend.exceptions.*;

import java.util.List;

public interface AdventureService {
    void addAdventure(AdventureDTO adventure) throws AdventureAlreadyExistsException, InvalidPriceException, InvalidPeopleNumberException, RequiredFiledException, InvalidAddressException;
    List<AdventureDTO> getInstructorAdventures(String email);

}
