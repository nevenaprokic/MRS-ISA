package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.AdditionalServiceDTO;
import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class AdditionalServiceController {

    @Autowired
    private OfferService offerService;

    @GetMapping("getAdditionalServiceInfo")
    @Transactional
    public ResponseEntity<List<AdditionalServiceDTO>> getAdditionalServiceByOffer(@RequestParam String id){
        try{
            List<AdditionalService> additionalServices = offerService.findAdditionalServiceByOffer(Integer.parseInt(id));
            List<AdditionalServiceDTO> additionalServiceDTO = new ArrayList<>();
            for(AdditionalService ad: additionalServices){
                AdditionalServiceDTO a = new AdditionalServiceDTO(ad);
                additionalServiceDTO.add(a);
            }
            return ResponseEntity.ok(additionalServiceDTO);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
