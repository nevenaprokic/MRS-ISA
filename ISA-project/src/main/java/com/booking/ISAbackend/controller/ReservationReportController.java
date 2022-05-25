package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.ReservationDTO;
import com.booking.ISAbackend.service.ReservationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reservation-report")
public class ReservationReportController {

    @Autowired
    private ReservationReportService reservationReportService;

    @GetMapping("get-all-by-cottage-owner")
    public ResponseEntity<List<Integer>> getReservationReportCottageOwner(@RequestParam String ownerEmail, @RequestParam String role){
        try{
            List<Integer> reservationsWithNoReport = reservationReportService.getReservationReportCottageOwner(ownerEmail);
            return ResponseEntity.ok().body(reservationsWithNoReport);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
