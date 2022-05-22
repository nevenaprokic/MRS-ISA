package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.*;
import com.booking.ISAbackend.exceptions.InvalidPriceException;
import com.booking.ISAbackend.exceptions.OfferNotAvailableException;
import com.booking.ISAbackend.exceptions.RequiredFiledException;
import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.model.Reservation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getAllReservation(String ownerId, String role) throws IOException;
    void makeReservation(ReservationParamsDTO params) throws OfferNotAvailableException;
    Boolean isAvailableOffer(Integer offerId,String startDate, Integer dayNum);
    List<ClientDTO> getClientByCottageOwnerEmail(String email);
    List<ClientDTO> getClientByShipOwnerEmail(String email);
    List<ClientDTO> getClientByInstructorEmail(String email);

    Integer makeReservationOwner(NewReservationDTO dto);
//    void addAdditionalServices(List<AdditionalServiceDTO> additionalServiceDTOs, Integer reservationId);
}
