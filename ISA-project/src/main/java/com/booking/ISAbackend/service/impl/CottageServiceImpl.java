package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.Cottage;
import com.booking.ISAbackend.repository.CottageRepository;
import com.booking.ISAbackend.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CottageServiceImpl implements CottageService {
    @Autowired
    private CottageRepository cottageRepository;


    @Override
    public List<Cottage> findAll(){
        return cottageRepository.findAll();
    }

    @Override
    public List<Cottage> findCottageByCottageOwnerEmail(String email) {
        return cottageRepository.findCottageByCottageOwnerEmail(email);
    }

    @Override
    public Cottage findCottageById(Integer id) {
        return  cottageRepository.findCottageById(id);
    }
    @Override
    public Address findAddressByCottageId(Integer id){
        Cottage cottage = cottageRepository.findCottageById(id);
        Address address = cottage.getAddress();
        return address;
    }

}
