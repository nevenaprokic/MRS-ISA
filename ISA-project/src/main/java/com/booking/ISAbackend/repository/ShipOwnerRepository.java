package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.ShipOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipOwnerRepository extends JpaRepository<ShipOwner, Integer> {
    ShipOwner findById(int id);
    ShipOwner findByEmail(String email);
}
