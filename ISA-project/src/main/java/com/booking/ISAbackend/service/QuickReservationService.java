package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.QuickActionDTO;
import com.booking.ISAbackend.model.QuickReservation;

import java.util.List;

public interface QuickReservationService {
    List<QuickActionDTO> findQuickReservationByOfferId(Integer id);
}
