package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.ReservationParamsDTO;
import com.booking.ISAbackend.email.EmailSender;
import com.booking.ISAbackend.exceptions.OfferNotAvailableException;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.*;
import com.booking.ISAbackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
}
