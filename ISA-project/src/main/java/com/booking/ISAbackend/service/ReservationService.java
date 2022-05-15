package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.ReservationParamsDTO;

public interface ReservationService {

    void makeReservation(ReservationParamsDTO params);
}
