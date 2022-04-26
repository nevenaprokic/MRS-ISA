package com.booking.ISAbackend.service;



import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.Ship;

import java.util.List;

public interface ShipService {
    List<Ship> findAll();
    List<Ship> findShipByShipOwnerEmail(String email);
    Ship findShipById(Integer id);
    Address findAddressByShipId(Integer id);
    List<Ship> searchShipByShipOwner(String name, Integer maxPeople, String address, Double price, String email);
}
