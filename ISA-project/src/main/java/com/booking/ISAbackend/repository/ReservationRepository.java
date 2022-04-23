package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    //List<Reservation> findClientsUpcomingReservations(String email);
}
