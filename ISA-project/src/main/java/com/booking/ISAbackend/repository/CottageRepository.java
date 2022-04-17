package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CottageRepository extends JpaRepository<Cottage, Integer> {
    List<Cottage> findCottageByCottageOwnerEmail(String email);
}
