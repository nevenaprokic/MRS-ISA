package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Offer findOfferById(Integer id);
}
