package com.booking.ISAbackend.service;

import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.model.Address;

import java.util.List;

public interface OfferService {
    Address findAddressByOfferId(Integer id);
    List<AdditionalService> findAdditionalServiceByOffer(Integer id);
}
