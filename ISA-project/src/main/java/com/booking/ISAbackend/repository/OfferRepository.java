package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Offer findOfferById(Integer id);

    @Modifying
    @Query("UPDATE Offer o SET o.deleted = true WHERE o.id = ?1")
    void updateDeleteByOfferId(Integer id);
}
