package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.AdventureDTO;
import com.booking.ISAbackend.dto.AdventureDetailsDTO;
import com.booking.ISAbackend.exceptions.*;

import java.util.List;

public interface AdventureService {
    void addAdventure(AdventureDTO adventure) throws AdventureAlreadyExistsException, InvalidPriceException, InvalidPeopleNumberException, RequiredFiledException, InvalidAddressException;
    List<AdventureDetailsDTO> getInstructorAdventures(String email);

    AdventureDetailsDTO findAdventureById(int parseInt) throws AdventureNotFoundException;
}
