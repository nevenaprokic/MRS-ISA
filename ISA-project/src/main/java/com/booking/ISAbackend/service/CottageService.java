package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.NewCottageDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.Cottage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface CottageService {
    List<Cottage> findAll();
    List<Cottage> findCottageByCottageOwnerEmail(String email);
    Cottage findCottageById(Integer id);
    Address findAddressByCottageId(Integer id);
    List<Cottage> searchCottages(String name, Integer maxPeople, String address, Double price);
    List<Cottage> searchCottagesClient(String name, String description, String address);
    List<Cottage> searchCottagesByCottageOwner(String name, Integer maxPeople, String address, Double price, String email);
    int addCottage(NewCottageDTO cottageDTO) throws CottageAlreadyExistsException, InvalidPriceException, InvalidPeopleNumberException, RequiredFiledException, InvalidAddressException, InvalidBedNumberException, InvalidRoomNumberException, IOException;
    void addAdditionalServices(List<HashMap<String, String>> additionalServiceDTO, int offerId) throws InvalidPriceException, RequiredFiledException;
}
