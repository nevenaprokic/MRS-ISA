package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.ClientDTO;
import com.booking.ISAbackend.dto.ReservationDTO;
import com.booking.ISAbackend.dto.ReservationParamsDTO;
import com.booking.ISAbackend.exceptions.OfferNotAvailableException;
import com.booking.ISAbackend.model.Reservation;

import java.io.IOException;
import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getAllReservation(String ownerId, String role) throws IOException;
    void makeReservation(ReservationParamsDTO params) throws OfferNotAvailableException;
    Boolean isAvailableOffer(Integer offerId,String startDate, Integer dayNum);
    List<ClientDTO> getClientByCottageOwnerEmail(String email);

}
