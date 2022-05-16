package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.AdditionalServiceDTO;
import com.booking.ISAbackend.dto.AddressDTO;
import com.booking.ISAbackend.exceptions.OfferNotFoundException;
import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.Offer;
import com.booking.ISAbackend.model.Reservation;
import com.booking.ISAbackend.repository.OfferRepository;
import com.booking.ISAbackend.repository.QuickReservationRepository;
import com.booking.ISAbackend.repository.ReservationRepository;
import com.booking.ISAbackend.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private QuickReservationRepository quickReservationRepository;

    @Override
    @Transactional
    public AddressDTO findAddressByOfferId(Integer id){
        Offer offer = offerRepository.findOfferById(id);
        Address address = offer.getAddress();
        AddressDTO addressDTO = new AddressDTO(address);
        return addressDTO;
    }

    @Override
    @Transactional
    public List<AdditionalServiceDTO> findAdditionalServiceByOffer(Integer id) {
        Offer offer = offerRepository.findOfferById(id);
        List<AdditionalService> additionalServices = offer.getAdditionalServices();
        List<AdditionalServiceDTO> additionalServiceDTO = new ArrayList<>();
        for(AdditionalService ad: additionalServices){
            AdditionalServiceDTO a = new AdditionalServiceDTO(ad);
            additionalServiceDTO.add(a);
        }
        return  additionalServiceDTO;
    }

    @Override
    @Transactional
    public void delete(Integer offerId) throws OfferNotFoundException {//slike, sub, additional
        Offer offer = offerRepository.findOfferById(offerId);
        if (offer == null)
            throw new OfferNotFoundException("Offer not found");

        if(offer.getQuickReservations().size() != 0)
            quickReservationRepository.deleteByOfferId(offerId);
        if(offer.getReservations().size() != 0)
            reservationRepository.deleteByOfferId(offerId);
        offerRepository.updateDeleteByOfferId(offerId);
    }
    @Override
    public Boolean checkOperationAllowed(Integer offerId) {
        List<Reservation> reservations = reservationRepository.findAllByOfferId(offerId);
        LocalDate today = LocalDate.now();
        for(Reservation r:reservations){
            if((today.compareTo(r.getEndDate())<0)){
                return false;
            }
        }
        return true;
    }

}
