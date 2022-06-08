package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Instructor;
import com.booking.ISAbackend.model.ShipOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipOwnerRepository extends JpaRepository<ShipOwner, Integer> {
    ShipOwner findById(int id);
    ShipOwner findByEmail(String email);


    @Query("SELECT s FROM ShipOwner  s INNER JOIN Ship sh WHERE sh.id = ?1 AND sh.shipOwner.id = s.id")
    ShipOwner findShipOwnerByShipId(int shipId);
}
