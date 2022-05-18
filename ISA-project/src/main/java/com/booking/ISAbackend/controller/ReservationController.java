package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.ReservationDTO;
import com.booking.ISAbackend.dto.ReservationParamsDTO;
import com.booking.ISAbackend.dto.ShipDTO;
import com.booking.ISAbackend.exceptions.OfferNotAvailableException;
import com.booking.ISAbackend.model.Reservation;
import com.booking.ISAbackend.service.ReservationService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("make")
    public ResponseEntity<String> makeReservation(@RequestBody ReservationParamsDTO params){
        try{
            reservationService.makeReservation(params);
            return ResponseEntity.ok("Reservation was successful!");
        }catch (OfferNotAvailableException ex){
            return ResponseEntity.status(400).body("Offer is not available in that time period.");
        }catch(Exception ex){
            return ResponseEntity.status(400).body("Something went wrong. Try again.");
        }

    }
    @GetMapping("get-all-by-cottage-owner")
    public ResponseEntity<List<ReservationDTO>> getReservation(@RequestParam String ownerId,@RequestParam String role){
        try{
            List<ReservationDTO> reservations = reservationService.getAllReservation(ownerId, role);
            return ResponseEntity.ok().body(reservations);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("get-all-by-ship-owner")
    public ResponseEntity<List<ReservationDTO>> getReservationByShipOwner(@RequestParam String ownerId,@RequestParam String role){
        try{
            List<ReservationDTO> reservations = reservationService.getAllReservation(ownerId, role);
            return ResponseEntity.ok().body(reservations);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
