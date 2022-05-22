package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.*;
import com.booking.ISAbackend.exceptions.OfferNotAvailableException;
import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.model.Reservation;
import com.booking.ISAbackend.service.ReservationService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("available-offer")
    public ResponseEntity<Boolean> isAvailableOffer(@RequestParam String offerId, @RequestParam String startDate, @RequestParam String dayNum){
        try{
            Boolean check = reservationService.isAvailableOffer(Integer.parseInt(offerId), startDate, Integer.parseInt(dayNum));
            return ResponseEntity.ok().body(check);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("make-by-owner")
    public ResponseEntity<Integer> makeReservationOwner(@RequestBody NewReservationDTO dto){
        try{
            Integer reservationId = reservationService.makeReservationOwner(dto);
            return ResponseEntity.ok(reservationId);
        }catch(Exception ex){
            return ResponseEntity.status(400).body(null);
        }

    }
//    @PostMapping("add-additional-services")
//    public ResponseEntity<String> addAdditionalServiceForCottage(@RequestBody Map<String, Object> data){
//        try{
//            HashMap<String, Object> paramsMap =  (HashMap<String, Object>) data.get("params");
//            Integer id = Integer.parseInt(paramsMap.get("reservationId").toString());
//            List<AdditionalServiceDTO> additionalServices = (List< AdditionalServiceDTO > ) paramsMap.get("additionalServiceDTO");
//
//            reservationService.addAdditionalServices(additionalServices, id);
//            return ResponseEntity.ok().body("Successfully added new  reservation");
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//    }
}
