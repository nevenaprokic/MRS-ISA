package com.booking.ISAbackend.service;

import com.booking.ISAbackend.model.Address;

public interface OfferService {
    Address findAddressByOfferId(Integer id);
}
