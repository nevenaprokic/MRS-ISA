package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.*;
import com.booking.ISAbackend.exceptions.OfferNotAvailableException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getAllReservation(String ownerId, String role) throws IOException;

    void makeReservation(ReservationParamsDTO params) throws OfferNotAvailableException;
    Boolean isAvailableOffer(Integer offerId,String startDate, Integer dayNum);
    Boolean isAvailableClient(String emailClient, String startReservation, String endReservation);
    List<ClientDTO> getClientByCottageOwnerEmail(String email);
    List<ClientDTO> getClientByShipOwnerEmail(String email);
    List<ClientDTO> getClientByInstructorEmail(String email);

    Integer makeReservationOwner(NewReservationDTO dto);

    List<ReservationDTO> getPastCottageReservationsByClient(String email) throws IOException;

    List<ReservationDTO> getPastShipReservationsByClient(String email) throws IOException;

    List<ReservationDTO> getPastAdventureReservationsByClient(String email) throws IOException;

    List<ReservationDTO> getUpcomingCottageReservationsByClient(String email) throws IOException;

    List<ReservationDTO> getUpcomingShipReservationsByClient(String email) throws IOException;

    List<ReservationDTO> getUpcomingAdventureReservationsByClient(String email) throws IOException;

}
