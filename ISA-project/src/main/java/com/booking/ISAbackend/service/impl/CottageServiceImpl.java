package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.Cottage;
import com.booking.ISAbackend.model.Photo;
import com.booking.ISAbackend.repository.CottageRepository;
import com.booking.ISAbackend.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Override
    public List<Cottage> searchCottages(String name, Integer maxPeople, String address, Double price) {
        return cottageRepository.searchCottages(name, maxPeople, address, price);
    }

    private List<String> getPhoto(Cottage c){
        List<String> photos = new ArrayList<>();
        for(Photo p: c.getPhotos()){
            photos.add(p.getPath());
        }
        return photos;
    }

}
