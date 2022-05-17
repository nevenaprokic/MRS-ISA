package com.booking.ISAbackend.controller;


import com.booking.ISAbackend.dto.CalendarItem;
import com.booking.ISAbackend.dto.ReservationDTO;
import com.booking.ISAbackend.dto.UnavailableDateDTO;
import com.booking.ISAbackend.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("calendar")
public class CalendarContoler {

    @Autowired
    CalendarService calendarService;

    @GetMapping("info")
    public ResponseEntity<List<CalendarItem>> getCalendarInfo(@RequestParam String ownerEmail,  @RequestParam int offerId){

        try{
            List<CalendarItem> calendarItems = calendarService.getCalendarInfo(ownerEmail, offerId);
            return ResponseEntity.ok(calendarItems);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("reservation-info")
    public ResponseEntity<ReservationDTO> getReservationDetails(@RequestParam int reservationId){
        try{
            ReservationDTO reservation = calendarService.getReservationDetails(reservationId);
            return ResponseEntity.ok(reservation);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
