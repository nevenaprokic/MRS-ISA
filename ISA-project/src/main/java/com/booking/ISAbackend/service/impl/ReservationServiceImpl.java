package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.ReservationDTO;
import com.booking.ISAbackend.dto.ReservationParamsDTO;
import com.booking.ISAbackend.email.EmailSender;
import com.booking.ISAbackend.exceptions.OfferNotAvailableException;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.*;
import com.booking.ISAbackend.service.AdditionalServiceService;
import com.booking.ISAbackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private AdditionalServiceService additionalServiceService;
    @Autowired
    private QuickReservationRepository quickReservationRepository;
    @Autowired
    private EmailSender emailSender;

    @Override
    @Transactional
    public void makeReservation(ReservationParamsDTO params) throws OfferNotAvailableException {
        List<Optional<AdditionalService>> services = new ArrayList<>();
        for(AdditionalService s : params.getServices()){
            services.add(additionalServiceRepository.findById(s.getId()));
        }
        Client user = clientRepository.findByEmail(params.getEmail());
        Offer offer = offerRepository.findOfferById(params.getOfferId());
        List<Offer> nonAvailable = offerRepository.nonAvailableOffers(params.getDate(), params.getEndingDate());

        for(Offer o: nonAvailable){
            if(Objects.equals(o.getId(), offer.getId()))
                throw new OfferNotAvailableException("Offer is not available in that period.");
        }

        for(UnavailableOfferDates ofd : offer.getUnavailableDate()){
            // (StartA <= EndB) and (EndA >= StartB)
            if(ofd.getEndDate().isAfter(params.getDate()) && ofd.getStartDate().isBefore(params.getEndingDate()))
                throw new OfferNotAvailableException("Offer is not available in that period.");
        }

        if(params.getActionId() != null)
            quickReservationRepository.deleteById(params.getActionId());

        List<AdditionalService> ys = new ArrayList<>();
        for (Optional<AdditionalService> x : services) {
            x.ifPresent(ys::add);
        }

        Reservation r = new Reservation(params.getDate(), params.getEndingDate(), ys, params.getTotal(), params.getGuests(), offer, user, false);
        reservationRepository.save(r);
        emailSender.reservationConfirmation(params);
    }

    @Override
    @Transactional
    public List<ReservationDTO> getPastCottageReservationsByClient(String email) throws IOException {
        List<Reservation> reservations = reservationRepository.getPastCottageReservationsByClient(email, LocalDate.now());
        return getReservationDTOS(reservations);
    }

    @Override
    @Transactional
    public List<ReservationDTO> getPastShipReservationsByClient(String email) throws IOException {
        List<Reservation> reservations = reservationRepository.getPastShipReservationsByClient(email, LocalDate.now());
        return getReservationDTOS(reservations);
    }

    @Override
    @Transactional
    public List<ReservationDTO> getPastAdventureReservationsByClient(String email) throws IOException {
        List<Reservation> reservations = reservationRepository.getPastAdventureReservationsByClient(email, LocalDate.now());
        return getReservationDTOS(reservations);
    }

    @Override
    @Transactional
    public Boolean isAvailableOffer(Integer offerId, String startDate, Integer dayNum) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDateReservation = LocalDate.parse(startDate, formatter);
        LocalDate endDateReservation = startDateReservation.plusDays(dayNum);
        List<Reservation> reservations = reservationRepository.findAllByOfferId(offerId);
        for(Reservation q: reservations){
            if((q.getStartDate().compareTo(startDateReservation) <= 0) && (startDateReservation.compareTo(q.getEndDate()) <= 0))
                return false;
            if((q.getStartDate().compareTo(endDateReservation) <= 0) && (endDateReservation.compareTo(q.getEndDate()) <= 0))
                return false;
        }
        return true;
    }


    @Override
    @Transactional
    public List<ReservationDTO> getAllReservation(String ownerId, String role) throws IOException {
        List<Reservation> reservations = new ArrayList<>();
        if(role.equals(UserType.SHIP_OWNER.toString()))
            reservations = reservationRepository.findPastReservationByShipOwnerEmail(ownerId, LocalDate.now());
        else if(role.equals(UserType.COTTAGE_OWNER.toString()))
            reservations = reservationRepository.findPastReservationByCottageOwnerEmail(ownerId, LocalDate.now());
        return getReservationDTOS(reservations);
    }

    private List<ReservationDTO> getReservationDTOS(List<Reservation> reservations) throws IOException {
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation r: reservations){
            ReservationDTO dto = new ReservationDTO(r);
            dto.setAdditionalServices(additionalServiceService.getAdditionalServices(r.getOffer()));
            reservationDTOS.add(dto);
        }
        return reservationDTOS;
    }

//    private String localDateToString(LocalDate date){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
//        return formatter.format(date);
//    }
//
//    private String getOfferPhoto(Offer offer) throws IOException {
//        String photoName = "no-image.png";
//        if(!offer.getPhotos().isEmpty()) {
//            photoName = offer.getPhotos().get(0).getPath();
//        }
//        return convertPhoto(photoName);
//    }
//
//    private String convertPhoto(String photoName) throws IOException {
//        String pathFile = "./src/main/frontend/src/components/images/" + photoName;
//        byte[] bytes = Files.readAllBytes(Paths.get(pathFile));
//        String photoData = Base64.getEncoder().encodeToString(bytes);
//        return photoData;
//    }
}
