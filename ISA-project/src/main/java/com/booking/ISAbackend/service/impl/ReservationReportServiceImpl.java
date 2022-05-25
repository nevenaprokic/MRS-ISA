package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.model.Reservation;
import com.booking.ISAbackend.model.ReservationReport;
import com.booking.ISAbackend.repository.ReservationReportRepository;
import com.booking.ISAbackend.repository.ReservationRepository;
import com.booking.ISAbackend.service.ReservationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationReportServiceImpl implements ReservationReportService {


    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationReportRepository reservationReportRepository;

    @Override
    public List<Integer> getReservationReportCottageOwner(String ownerEmail) {
        LocalDate today = LocalDate.now();
        List<Integer> reservationWithReport = reservationRepository.findReservationWithNoReportByCottageOwnerEmail(ownerEmail, today);
        List<Reservation> reservations = reservationRepository.findPastReservationByCottageOwnerEmail(ownerEmail,today);
        List<Integer> reservationWithNoReport = new ArrayList<>();
        for(Reservation r: reservations) {
            if(!reservationWithReport.contains(r.getId())){
                reservationWithNoReport.add(r.getId());
            }
        }
        return reservationWithNoReport;
    }
}
