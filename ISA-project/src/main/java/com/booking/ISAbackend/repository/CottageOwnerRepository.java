package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.CottageOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CottageOwnerRepository extends JpaRepository<CottageOwner, Integer> {
    CottageOwner findById(int id);
    CottageOwner findByEmail(String email);
}
