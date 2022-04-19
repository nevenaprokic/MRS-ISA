package com.booking.ISAbackend.service;



import com.booking.ISAbackend.model.Ship;

import java.util.List;

public interface ShipService {
    List<Ship> findAll();
    List<Ship> findShipByShipOwnerEmail(String email);
}
