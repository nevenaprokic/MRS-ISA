package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.ReservationDTO;
import com.booking.ISAbackend.dto.ReservationParamsDTO;

import java.io.IOException;
import java.util.List;

public interface ReservationService {

    void makeReservation(ReservationParamsDTO params);
    List<ReservationDTO> getAllReservation(String ownerId) throws IOException;
}
