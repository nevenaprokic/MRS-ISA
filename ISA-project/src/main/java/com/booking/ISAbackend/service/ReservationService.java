package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.ReservationDTO;
import com.booking.ISAbackend.dto.ReservationParamsDTO;
import com.booking.ISAbackend.exceptions.OfferNotAvailableException;

import java.io.IOException;
import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getAllReservation(String ownerId) throws IOException;
    void makeReservation(ReservationParamsDTO params) throws OfferNotAvailableException;

}
