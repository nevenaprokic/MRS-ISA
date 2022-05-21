package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.QuickReservationDTO;

import java.util.List;

public interface QuickReservationService {
    List<QuickReservationDTO> findQuickReservationByOfferId(Integer id);
    Boolean checkQuickReservationByOfferId(Integer offerId, String startDate, Integer dateNumber);
}
