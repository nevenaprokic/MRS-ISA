package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.ReservationDTO;
import com.booking.ISAbackend.dto.ReservationParamsDTO;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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

    @Override
    public void makeReservation(ReservationParamsDTO params) {
        List<Optional<AdditionalService>> services = new ArrayList<>();
        for(AdditionalService s : params.getServices()){
            services.add(additionalServiceRepository.findById(s.getId()));
        }
        Client user = clientRepository.findByEmail(params.getEmail());
        Offer offer = offerRepository.findOfferById(params.getOfferId());

        List<AdditionalService> ys = new ArrayList<>();
        for (Optional<AdditionalService> x : services) {
            x.ifPresent(ys::add);
        }

        Reservation r = new Reservation(params.getDate(), params.getEndingDate(), ys, params.getTotal(), params.getGuests(), offer, user, false);
        reservationRepository.save(r);
    }

    @Override
    @Transactional
    public List<ReservationDTO> getAllReservation(String ownerId) throws IOException {
        List<Reservation> reservations = reservationRepository.findPastReservationByCottageOwnerEmail(ownerId, LocalDate.now());
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation r: reservations){
            reservationDTOS.add(new ReservationDTO(r.getId(),
                    localDateToString(r.getStartDate()),
                    localDateToString(r.getEndDate()),
                    additionalServiceService.getAdditionalServices(r.getOffer()),
                    r.getPrice(),
                    r.getNumberOfPerson(),
                    r.getOffer().getId(),
                    r.getOffer().getName(),
                    r.getClient().getId(),
                    r.getClient().getFirstName(),
                    r.getClient().getLastName(),
                    getOfferPhoto(r.getOffer()),
                    r.getClient().getPhoneNumber(),
                    r.getClient().getEmail()));
        }
        return reservationDTOS;
    }
    private String localDateToString(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return formatter.format(date);
    }

    private String getOfferPhoto(Offer offer) throws IOException {
        String photoName = "no-image.png";
        if(!offer.getPhotos().isEmpty()) {
            photoName = offer.getPhotos().get(0).getPath();
        }
        return convertPhoto(photoName);
    }

    private String convertPhoto(String photoName) throws IOException {
        String pathFile = "./src/main/frontend/src/components/images/" + photoName;
        byte[] bytes = Files.readAllBytes(Paths.get(pathFile));
        String photoData = Base64.getEncoder().encodeToString(bytes);
        return photoData;
    }
}
