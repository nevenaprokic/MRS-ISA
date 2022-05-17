package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.ReservationParamsDTO;
import com.booking.ISAbackend.exceptions.OfferNotAvailableException;

public interface ReservationService {

    void makeReservation(ReservationParamsDTO params) throws OfferNotAvailableException;
}
