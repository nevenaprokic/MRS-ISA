package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Cottage;
import com.booking.ISAbackend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findAllByOfferId(Integer id);
  
    @Query("SELECT r FROM Reservation r JOIN FETCH r.client WHERE r.client.id = ?1 AND r.endDate > CURRENT_DATE")
    List<Reservation> findClientsUpcomingReservations(Integer id);

    @Query("SELECT r FROM Reservation r INNER JOIN Cottage c ON r.offer.id = c.id INNER JOIN Owner ow ON ow.id = c.cottageOwner.id AND ow.email = ?1")
    List<Reservation> findByCottageOwnerEmail(String email);

    @Query("SELECT r FROM Reservation r INNER JOIN Ship s ON r.offer.id = s.id INNER JOIN Owner ow ON ow.id = s.shipOwner.id AND ow.email = ?1")
    List<Reservation> findByShipOwnerEmail(String email);

    @Query("SELECT DISTINCT c FROM Reservation r INNER JOIN Cottage c ON r.offer.id = c.id WHERE" +
            " (r.startDate < ?1 AND r.endDate > ?1) OR (r.startDate > ?1 AND r.startDate < ?2) ")
    List<Cottage> nonAvailableCottages(LocalDate from, LocalDate to);
}
