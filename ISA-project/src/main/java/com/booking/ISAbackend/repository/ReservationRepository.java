package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findAllByOfferId(Integer id);
  
    @Query("SELECT r FROM Reservation r JOIN FETCH r.client WHERE r.client.id = ?1 AND r.endDate > CURRENT_DATE")
    List<Reservation> findClientsUpcomingReservations(Integer id);
}
