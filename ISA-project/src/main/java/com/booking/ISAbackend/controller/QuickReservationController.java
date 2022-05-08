package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.QuickActionDTO;
import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.model.QuickReservation;
import com.booking.ISAbackend.service.QuickReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class QuickReservationController {
    @Autowired
    private QuickReservationService quickReservationService;

    @GetMapping("getQuickActions")
    @Transactional

    public ResponseEntity<List<QuickActionDTO>> getQuickActionsByOfferId(@RequestParam String id){
        try {
            List<QuickReservation> quickReservations = quickReservationService.findQuickReservationByOfferId(Integer.parseInt(id));
            List<QuickActionDTO> dto = new ArrayList<>();
            for(QuickReservation res: quickReservations){
                QuickActionDTO quickActionDTO = new QuickActionDTO(res);
                dto.add(quickActionDTO);
            }
            return ResponseEntity.ok(dto);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
