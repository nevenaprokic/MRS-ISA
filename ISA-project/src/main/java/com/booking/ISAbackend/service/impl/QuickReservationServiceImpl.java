package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.QuickActionDTO;
import com.booking.ISAbackend.model.QuickReservation;
import com.booking.ISAbackend.repository.QuickReservationRepository;
import com.booking.ISAbackend.service.QuickReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuickReservationServiceImpl implements QuickReservationService {

    @Autowired
    private QuickReservationRepository quickReservationRepository;

    @Override
    @Transactional
    public List<QuickActionDTO> findQuickReservationByOfferId(Integer id){
        List<QuickReservation> listOfAllQuickReservation = quickReservationRepository.findQuickReservationByOfferId(id);
        List<QuickReservation> listOfCurrentQuickReservation = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for(QuickReservation qr:listOfAllQuickReservation){
            if((today.compareTo(qr.getStartDateAction())>0)&&(today.compareTo(qr.getEndDateAction())<0)){
                listOfCurrentQuickReservation.add(qr);
            }
        }
        List<QuickActionDTO> dto = new ArrayList<>();
        for(QuickReservation res: listOfCurrentQuickReservation){
            QuickActionDTO quickActionDTO = new QuickActionDTO(res);
            dto.add(quickActionDTO);
        }
        return dto;

    }
}
