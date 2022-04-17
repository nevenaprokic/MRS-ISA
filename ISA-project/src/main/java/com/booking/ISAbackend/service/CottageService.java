package com.booking.ISAbackend.service;

import com.booking.ISAbackend.model.Cottage;

import java.util.List;

public interface CottageService {
    List<Cottage> findAll();
    List<Cottage> findCottageByCottageOwnerEmail(String email);
}
