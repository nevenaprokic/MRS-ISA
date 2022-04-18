package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.QuickReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuickReservationRepository extends JpaRepository<QuickReservation, Integer> {
    List<QuickReservation> findQuickReservationByOfferId(Integer id);
}
