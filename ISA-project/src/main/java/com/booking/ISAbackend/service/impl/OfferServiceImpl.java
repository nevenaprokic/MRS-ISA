package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.AdditionalServiceDTO;
import com.booking.ISAbackend.dto.AddressDTO;
import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.Offer;
import com.booking.ISAbackend.repository.OfferRepository;
import com.booking.ISAbackend.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepository offerRepository;

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

}
