package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.*;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.model.Reservation;
import com.booking.ISAbackend.service.ReservationService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDate;
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
        }catch (NotAllowedToMakeReservationException ex){
            return ResponseEntity.status(400).body("You are not allowed to make a reservation because of penalties.");
        }catch (PreviouslyCanceledReservationException ex){
            return ResponseEntity.status(400).body("Reservation has already been reserved and canceled.");
        }catch (ClientNotAvailableException ex){
            return ResponseEntity.status(400).body("Client is not available in this time period.");
        }catch(Exception ex){
            return ResponseEntity.status(400).body("Something went wrong. Try again.");
        }
    }

    @PostMapping("confirm-email")
    public ResponseEntity<String> sendCofirmationForReservation(@RequestBody ReservationParamsDTO params){
        try{
            reservationService.sendEmail(params);
            return ResponseEntity.ok("Reservation was successful!");
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

    @GetMapping("get-cottage-reservations-by-client")
    public ResponseEntity<List<ReservationDTO>> getPastCottageReservationsClient(@RequestParam String email) {
        try {
            List<ReservationDTO> reservations = reservationService.getPastCottageReservationsByClient(email);
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
        Integer reservationId = reservationService.makeReservationOwner(dto);
        return ResponseEntity.ok(reservationId);
//        try{
//            Integer reservationId = reservationService.makeReservationOwner(dto);
//            return ResponseEntity.ok(reservationId);
//        }catch(Exception ex){
//            return ResponseEntity.status(400).body(null);
//        }

    }

    @GetMapping("get-ship-reservations-by-client")
    public ResponseEntity<List<ReservationDTO>> getPastShipReservationsClient(@RequestParam String email){
        try{
            List<ReservationDTO> reservations = reservationService.getPastShipReservationsByClient(email);
            return ResponseEntity.ok().body(reservations);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-adventure-reservations-by-client")
    public ResponseEntity<List<ReservationDTO>> getPastAdventureReservationsClient(@RequestParam String email){
        try{
            List<ReservationDTO> reservations = reservationService.getPastAdventureReservationsByClient(email);
            return ResponseEntity.ok().body(reservations);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("get-upcoming-cottage-reservations-by-client")
    public ResponseEntity<List<ReservationDTO>> getUpcomingCottageReservationsClient(@RequestParam String email) {
        try {
            List<ReservationDTO> reservations = reservationService.getUpcomingCottageReservationsByClient(email);
            return ResponseEntity.ok().body(reservations);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-upcoming-ship-reservations-by-client")
    public ResponseEntity<List<ReservationDTO>> getUpcomingShipReservationsClient(@RequestParam String email){
        try{
            List<ReservationDTO> reservations = reservationService.getUpcomingShipReservationsByClient(email);
            return ResponseEntity.ok().body(reservations);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-upcoming-adventure-reservations-by-client")
    public ResponseEntity<List<ReservationDTO>> getUpcomingAdventureReservationsClient(@RequestParam String email){
        try{
            List<ReservationDTO> reservations = reservationService.getUpcomingAdventureReservationsByClient(email);
            return ResponseEntity.ok().body(reservations);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable Integer id) {
        try {
            reservationService.cancelReservation(id);
            return new ResponseEntity<>("Reservation successfully canceled!", HttpStatus.OK);

        } catch (CancellingReservationException e) {
            return new ResponseEntity<>("Cannot cancel reservation less than 3 days before it starts.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-attendance-report-yearly-cottage")
    public ResponseEntity<List<AttendanceReportDTO>> getAttendanceReportYearlyCottage(@RequestParam String email, @RequestParam String date){
        try{
            List<AttendanceReportDTO> report = reservationService.getAttendanceReportYearlyCottage(email, date);
            return ResponseEntity.ok().body(report);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("get-attendance-report-monthly-cottage")
    public ResponseEntity<List<AttendanceReportDTO>> getAttendanceReportMonthlyCottage(@RequestParam String email, @RequestParam String date){
        try{
          List<AttendanceReportDTO> report = reservationService.getAttendanceReportMonthlyCottage(email, date);
          return ResponseEntity.ok().body(report);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-attendance-report-weekly-cottage")
    public ResponseEntity<List<AttendanceReportDTO>> getAttendanceReportWeeklyCottage(@RequestParam String email, @RequestParam String date){
        try{
          List<AttendanceReportDTO> report = reservationService.getAttendanceReportWeeklyCottage(email, date);
          return ResponseEntity.ok().body(report);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("get-attendance-report-yearly-ship")
    public ResponseEntity<List<AttendanceReportDTO>> getAttendanceReportYearlyShip(@RequestParam String email, @RequestParam String date){
        try{
            List<AttendanceReportDTO> report = reservationService.getAttendanceReportYearlyShip(email, date);
            return ResponseEntity.ok().body(report);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("get-attendance-report-monthly-ship")
    public ResponseEntity<List<AttendanceReportDTO>> getAttendanceReportMonthlyShip(@RequestParam String email, @RequestParam String date){
        try{
            List<AttendanceReportDTO> report = reservationService.getAttendanceReportMonthlyShip(email, date);
            return ResponseEntity.ok().body(report);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-attendance-report-weekly-ship")
    public ResponseEntity<List<AttendanceReportDTO>> getAttendanceReportWeeklyShip(@RequestParam String email, @RequestParam String date){
        try{
            List<AttendanceReportDTO> report = reservationService.getAttendanceReportWeeklyShip(email, date);
            return ResponseEntity.ok().body(report);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("get-attendance-report-yearly-adventure")
    public ResponseEntity<List<AttendanceReportDTO>> getAttendanceReportYearlyAdventure(@RequestParam String email, @RequestParam String date){
        try{
            List<AttendanceReportDTO> report = reservationService.getAttendanceReportYearlyAdventure(email, date);
            return ResponseEntity.ok().body(report);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("get-attendance-report-monthly-adventure")
    public ResponseEntity<List<AttendanceReportDTO>> getAttendanceReportMonthlyAdventure(@RequestParam String email, @RequestParam String date){
        try{
            List<AttendanceReportDTO> report = reservationService.getAttendanceReportMonthlyAdventure(email, date);
            return ResponseEntity.ok().body(report);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-attendance-report-weekly-adventure")
    public ResponseEntity<List<AttendanceReportDTO>> getAttendanceReportWeeklyAdventure(@RequestParam String email, @RequestParam String date){
        try{
            List<AttendanceReportDTO> report = reservationService.getAttendanceReportWeeklyAdventure(email, date);
            return ResponseEntity.ok().body(report);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("instructor-history")
    public ResponseEntity<List<ReservationDTO>> getReservationByInstructor(@RequestParam String ownerId,@RequestParam String role){
        try{
            List<ReservationDTO> reservations = reservationService.getAllReservation(ownerId, role);
            System.out.println(reservations.size());
            return ResponseEntity.ok().body(reservations);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

}
