package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer>{
    @Query("SELECT m FROM Mark m INNER JOIN Reservation r ON m.id = r.id INNER JOIN Offer o ON o.id = r.offer.id AND o.id = ?1")
    List<Mark> findAllMarkByOfferId(Integer idOffer);
}
