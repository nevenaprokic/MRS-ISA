package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.model.QuickReservation;
import com.booking.ISAbackend.repository.QuickReservationRepository;
import com.booking.ISAbackend.service.QuickReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuickReservationServiceImpl implements QuickReservationService {

    @Autowired
    private QuickReservationRepository quickReservationRepository;

    @Override
    public List<QuickReservation> findQuickReservationByOfferId(Integer id){
        return quickReservationRepository.findQuickReservationByOfferId(id);

    }
}
