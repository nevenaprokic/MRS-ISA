package com.booking.ISAbackend.service;

import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.Cottage;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

public interface CottageService {
    List<Cottage> findAll();
    List<Cottage> findCottageByCottageOwnerEmail(String email);
    Cottage findCottageById(Integer id);
    Address findAddressByCottageId(Integer id);
    List<Cottage> searchCottages(String name, Integer maxPeople, String address, Double price);
    List<Cottage> searchCottagesByCottageOwner(String name, Integer maxPeople, String address, Double price, String email);
}
