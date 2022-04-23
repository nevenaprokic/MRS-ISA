package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r JOIN FETCH r.client WHERE r.client.id = ?1")
    List<Reservation> findClientsUpcomingReservations(Integer id);
}
