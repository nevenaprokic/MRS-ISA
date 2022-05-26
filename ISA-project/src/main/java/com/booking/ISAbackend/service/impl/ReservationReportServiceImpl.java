package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.NewReservationReportDTO;
import com.booking.ISAbackend.model.Client;
import com.booking.ISAbackend.model.Impression;
import com.booking.ISAbackend.model.Reservation;
import com.booking.ISAbackend.model.ReservationReport;
import com.booking.ISAbackend.repository.ClientRepository;
import com.booking.ISAbackend.repository.ReservationReportRepository;
import com.booking.ISAbackend.repository.ReservationRepository;
import com.booking.ISAbackend.service.ReservationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationReportServiceImpl implements ReservationReportService {


    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationReportRepository reservationReportRepository;
    @Autowired
    private ClientRepository clientRepository;

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
    @Override
    public void addReservationReport(NewReservationReportDTO dto){
        Optional<Client> client = clientRepository.findById(dto.getClientId());
        Optional<Reservation> reservation = reservationRepository.findById(dto.getReservationId());
        Boolean penalOption = false;
        if(Impression.NEGATIVE == dto.getValueImpression())
            penalOption = true;
        Boolean automaticallyPenal = false;
        if(!dto.getValueShowUp())
            automaticallyPenal = true;
        if(client.isPresent() && reservation.isPresent()){
            ReservationReport reservationReport = new ReservationReport(penalOption,automaticallyPenal, dto.getComment(),reservation.get(),client.get());
            reservationReportRepository.save(reservationReport);
        }

    }
}
