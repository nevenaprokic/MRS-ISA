package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.NewReservationReportDTO;
import com.booking.ISAbackend.dto.OfferForReportDTO;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.*;
import com.booking.ISAbackend.service.ReservationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private AdventureReporitory adventureReporitory;
    @Autowired
    private ShipRepository shipRepository;

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
    public List<Integer> getReservationReportShipOwner(String ownerEmail) {
        LocalDate today = LocalDate.now();
        List<Integer> reservationWithReport = reservationRepository.findReservationWithNoReportByShipOwnerEmail(ownerEmail, today);
        List<Reservation> reservations = reservationRepository.findPastReservationByShipOwnerEmail(ownerEmail,today);
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

    @Override
    @Transactional
    public List<OfferForReportDTO> getReportIncomeStatementCottage(String start, String end, String email) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);

        List<Reservation> reservations = reservationRepository.findPastReservationByCottageOwnerEmail(email,LocalDate.now());

        List<OfferForReportDTO> offers = listInitialization(email);
        getReportList(reservations, startDate, endDate, offers);
        return offers;
    }

    @Override
    @Transactional
    public List<OfferForReportDTO> getReportIncomeStatementShip(String start, String end, String email) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);

        List<Reservation> reservations = reservationRepository.findPastReservationByShipOwnerEmail(email,LocalDate.now());

        List<OfferForReportDTO> offers = listInitializationShip(email);
        return  getReportList(reservations, startDate, endDate, offers);

    }

    private List<OfferForReportDTO> getReportList(List<Reservation> reservations, LocalDate startDate, LocalDate endDate, List<OfferForReportDTO> offers) {
        Double totalPrice = 0.0;
        Integer numberOfReservation = 0;

        for(Reservation r: reservations){
            if((r.getStartDate().compareTo(startDate)>=0)&&(r.getStartDate().compareTo(endDate)<=0)){
                totalPrice += r.getPrice();
                numberOfReservation++;
                for(OfferForReportDTO of: offers){
                    if(of.getOfferName().equals(r.getOffer().getName())){
                        of.setNumberOfReservation((of.getNumberOfReservation()+1));
                        of.setTotalPrice((of.getTotalPrice()+r.getPrice()));
                        break;
                    }
                }

            }
        }
        offers.add(new OfferForReportDTO("total", numberOfReservation, totalPrice));
        return offers;
    }

    @Override
    @Transactional
    public List<OfferForReportDTO> getReportIncomeStatementAdventure(String start, String end, String email) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);

        List<Reservation> reservations = reservationRepository.findPastReservationByInstructorEmail(email,LocalDate.now());

        List<OfferForReportDTO> offers = listInitializationAdventure(email);
        return  getReportList(reservations, startDate, endDate, offers);
    }

    private ArrayList<OfferForReportDTO> listInitializationAdventure(String email){
        ArrayList<OfferForReportDTO> offers = new ArrayList<OfferForReportDTO>();
        List<Adventure> adventures = adventureReporitory.findAdventureByInstructorEmail(email);
        for(Adventure a: adventures){
            offers.add(new OfferForReportDTO(a.getName(),0,0.0));
        }
        return offers;
    }

    private ArrayList<OfferForReportDTO> listInitializationShip(String email){
        ArrayList<OfferForReportDTO> offers = new ArrayList<OfferForReportDTO>();
        List<Ship> ships = shipRepository.findShipByShipOwnerEmail(email);
        for(Ship s: ships){
            offers.add(new OfferForReportDTO(s.getName(),0,0.0));
        }
        return offers;
    }

    private ArrayList<OfferForReportDTO> listInitialization(String email){
        ArrayList<OfferForReportDTO> offers = new ArrayList<OfferForReportDTO>();
        List<Cottage> cottages = cottageRepository.findCottageByCottageOwnerEmail(email);
        for(Cottage c: cottages){
            offers.add(new OfferForReportDTO(c.getName(),0,0.0));
        }
        return offers;
    }
}