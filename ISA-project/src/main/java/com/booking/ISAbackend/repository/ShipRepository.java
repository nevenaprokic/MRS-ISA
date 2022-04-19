package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Integer> {
    List<Ship> findShipByShipOwnerEmail(String email);
    Ship findShipById(Integer id);
}
