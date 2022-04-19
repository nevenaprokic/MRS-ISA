package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.Offer;
import com.booking.ISAbackend.repository.OfferRepository;
import com.booking.ISAbackend.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepository offerRepository;

    @Override
    public Address findAddressByOfferId(Integer id){
        Offer offer = offerRepository.findOfferById(id);
        Address address = offer.getAddress();
        return address;
    }
}
