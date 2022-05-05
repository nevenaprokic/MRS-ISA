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

    @Query("SELECT r FROM Reservation r INNER JOIN Cottage c ON r.offer.id = c.id INNER JOIN Owner ow ON ow.id = c.cottageOwner.id AND ow.email = ?1")
    List<Reservation> findByCottageOwnerEmail(String email);
    // c.id INNER JOIN Owner ow ON c.cottageOwner.id = ow.id AND ow.email = ?1
    //(value = "SELECT r FROM Reservation r INNER JOIN Cottage c ON r.offer.id " , nativeQuery = true
}
