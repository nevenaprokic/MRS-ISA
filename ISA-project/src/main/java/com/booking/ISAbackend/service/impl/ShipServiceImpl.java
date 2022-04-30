package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.Cottage;
import com.booking.ISAbackend.model.Ship;
import com.booking.ISAbackend.repository.ShipRepository;
import com.booking.ISAbackend.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private ShipRepository shipRepository;

    @Override
    public List<Ship> findAll() {
        return  shipRepository.findAll();
    }

    @Override
    public List<Ship> findShipByShipOwnerEmail(String email) {
        return shipRepository.findShipByShipOwnerEmail(email);
    }

    @Override
    public Ship findShipById(Integer id) {
        return shipRepository.findShipById(id);
    }

    @Override
    public Address findAddressByShipId(Integer id) {
        Ship ship = shipRepository.findShipById(id);
        Address address = ship.getAddress();
        return address;
    }

    @Override
    public List<Ship> searchShips(String name, Integer maxPeople, String address, Double price) {
        return shipRepository.searchShips(name, maxPeople, address, price);
    }

    @Override
    public List<Ship> searchShipByShipOwner(String name, Integer maxPeople, String address, Double price, String email) {
        return shipRepository.searchShipsByShipOwnerEmail(name, maxPeople, address, price, email);
    }
}
