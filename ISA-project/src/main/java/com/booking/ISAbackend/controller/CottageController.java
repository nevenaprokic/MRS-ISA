package com.booking.ISAbackend.controller;


import com.booking.ISAbackend.dto.CottageDTO;
import com.booking.ISAbackend.model.Cottage;
import com.booking.ISAbackend.model.Photo;
import com.booking.ISAbackend.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping
public class CottageController {
    @Autowired
    private CottageService cottageService;

    @GetMapping("getCottages")
    @Transactional
    public ResponseEntity<List<CottageDTO>> getCottageByCottageOwnerEmail(@RequestParam String email){
        try{
            List<Cottage> cottages = cottageService.findCottageByCottageOwnerEmail(email);
            List<CottageDTO> dto = new ArrayList<>();
            for(Cottage c: cottages){
                CottageDTO cottageDTO = new CottageDTO(c.getId(),c.getName(), c.getDescription(),
                        c.getPrice(), getPhoto(c), c.getNumberOfPerson(), c.getRulesOfConduct(), c.getCancellationConditions(),
                        c.getRoomNumber(), c.getBedNumber());
                dto.add(cottageDTO);
            }
            return ResponseEntity.ok(dto);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getCottageInfo")
    @Transactional
    public ResponseEntity<CottageDTO> getCottageInfo(@RequestParam String idCottage){
        try{
            Cottage cottage = cottageService.findCottageById(Integer.parseInt(idCottage));

            if(cottage == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            CottageDTO cottageDTO = new CottageDTO(cottage.getId(),cottage.getName(), cottage.getDescription(),
                    cottage.getPrice(), getPhoto(cottage), cottage.getNumberOfPerson(), cottage.getRulesOfConduct(),
                    cottage.getCancellationConditions(), cottage.getRoomNumber(), cottage.getBedNumber());
            return  ResponseEntity.ok(cottageDTO);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
    private List<String> getPhoto(Cottage c){
        List<String> photos = new ArrayList<>();
        for(Photo p: c.getPhotos()){
            photos.add(p.getPath());
        }
        return photos;
    }

    @GetMapping("getAllCottages")
    @Transactional
    public ResponseEntity<List<CottageDTO>> getCottages(){
        try{
            List<Cottage> cottages = cottageService.findAll();
            List<CottageDTO> dto = new ArrayList<>();
            for(Cottage c: cottages){
                CottageDTO cottageDTO = new CottageDTO(c);
                dto.add(cottageDTO);
            }
            return ResponseEntity.ok(dto);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("searchCottages")
    @Transactional
    public ResponseEntity<List<CottageDTO>> searchCottages(@RequestParam String name, @RequestParam String address, @RequestParam Integer maxPeople, @RequestParam Double price){

        try{
            List<Cottage> cottages = cottageService.searchCottages(name, maxPeople, address, price);
            List<CottageDTO> dto = new ArrayList<>();
            for(Cottage c: cottages){
                CottageDTO cottageDTO = new CottageDTO(c);
                dto.add(cottageDTO);
            }
            return ResponseEntity.ok(dto);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
