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
                CottageDTO cottageDTO = new CottageDTO(c);
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

            CottageDTO cottageDTO = new CottageDTO(cottage);
            return  ResponseEntity.ok(cottageDTO);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

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
    @GetMapping("searchCottagesByCottageOwner")
    @Transactional
    public ResponseEntity<List<CottageDTO>> searchCottagesByCottageOwner(@RequestParam String name, @RequestParam String address, @RequestParam Integer maxPeople, @RequestParam Double price, @RequestParam String cottageOwnerUsername){
        try{
            List<Cottage> cottages = cottageService.searchCottagesByCottageOwner(name, maxPeople, address, price, cottageOwnerUsername);
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
