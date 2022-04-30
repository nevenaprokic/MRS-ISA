package com.booking.ISAbackend.service;



import com.booking.ISAbackend.dto.NewShipDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.Ship;

import java.util.List;

public interface ShipService {
    List<Ship> findAll();
    List<Ship> findShipByShipOwnerEmail(String email);
    Ship findShipById(Integer id);
    Address findAddressByShipId(Integer id);
    public List<Ship> searchShips(String name, Integer maxPeople, String address, Double price);
    List<Ship> searchShipByShipOwner(String name, Integer maxPeople, String address, Double price, String email);
    void addShip(NewShipDTO shipDTO) throws InvalidMotorNumberException, InvalidMaxSpeedException, InvalidSizeException, InvalidMotorPowerException, InvalidPriceException, InvalidPeopleNumberException, ShipAlreadyExistsException, InvalidAddressException;
}
