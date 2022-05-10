package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.QuickReservationDTO;
import com.booking.ISAbackend.service.QuickReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("quick-reservation")
public class QuickReservationController {
    @Autowired
    private QuickReservationService quickReservationService;

    @GetMapping("get")
    public ResponseEntity<List<QuickReservationDTO>> getQuickReservationsByOfferId(@RequestParam String id){
        try {
            List<QuickReservationDTO> quickReservations = quickReservationService.findQuickReservationByOfferId(Integer.parseInt(id));

            return ResponseEntity.ok(quickReservations);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
