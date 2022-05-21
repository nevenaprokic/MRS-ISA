package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.ReservationDTO;
import com.booking.ISAbackend.dto.ReservationParamsDTO;
import com.booking.ISAbackend.exceptions.OfferNotAvailableException;
import com.booking.ISAbackend.model.Reservation;

import java.io.IOException;
import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getAllReservation(String ownerId, String role) throws IOException;

    void makeReservation(ReservationParamsDTO params) throws OfferNotAvailableException;

    List<ReservationDTO> getPastCottageReservationsByClient(String email) throws IOException;

    List<ReservationDTO> getPastShipReservationsByClient(String email) throws IOException;

    List<ReservationDTO> getPastAdventureReservationsByClient(String email) throws IOException;
}
