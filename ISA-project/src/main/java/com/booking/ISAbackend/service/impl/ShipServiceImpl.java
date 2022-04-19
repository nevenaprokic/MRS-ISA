package com.booking.ISAbackend.service.impl;

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
}
