package com.booking.ISAbackend.controller;


import com.booking.ISAbackend.dto.CottageDTO;
import com.booking.ISAbackend.model.Cottage;
import com.booking.ISAbackend.model.Photo;
import com.booking.ISAbackend.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CottageController {
    @Autowired
    private CottageService cottageService;

    @GetMapping("getCottages")
    @Transactional
    public ResponseEntity<List<CottageDTO>> getCottageByCottageOwnerEmail(@RequestParam String email){
        List<Cottage> cottages = cottageService.findCottageByCottageOwnerEmail(email);
        List<CottageDTO> dto = new ArrayList<>();
        for(Cottage c: cottages){
            List<String> photos = new ArrayList<>();
            for(Photo p: c.getPhotos()){
                photos.add(p.getPath());
            }
            System.out.println(c.getPhotos());
            CottageDTO cottageDTO = new CottageDTO(c.getName(), c.getDescription(),
            c.getPrice(), photos, c.getNumberOfPerson(), c.getRulesOfConduct(), c.getCancellationConditions());
            dto.add(cottageDTO);
        }
        return ResponseEntity.ok(dto);

    }
}