package com.booking.ISAbackend.service;

import com.booking.ISAbackend.model.QuickReservation;

import java.util.List;

public interface QuickReservationService {
    List<QuickReservation> findQuickReservationByOfferId(Integer id);
}
