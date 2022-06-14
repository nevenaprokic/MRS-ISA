package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Instructor;
import com.booking.ISAbackend.model.ShipOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipOwnerRepository extends JpaRepository<ShipOwner, Integer> {
    ShipOwner findById(int id);
    ShipOwner findByEmail(String email);

    @Query("SELECT DISTINCT s FROM ShipOwner  s INNER JOIN Ship sh ON sh.id = ?1 WHERE sh.shipOwner.id = s.id")
    ShipOwner findShipOwnerByShipId(int shipId);

    @Query( "Select count(distinct s) FROM ShipOwner s WHERE s.deleted = false")
    int getNumberOfShipOwners();

    @Query("SELECT s FROM ShipOwner s WHERE s.deleted = false")
    Page<ShipOwner> findAllActiveUsers(PageRequest request);

}
