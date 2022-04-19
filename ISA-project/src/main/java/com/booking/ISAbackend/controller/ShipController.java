package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.ShipDTO;
import com.booking.ISAbackend.model.Cottage;
import com.booking.ISAbackend.model.Photo;
import com.booking.ISAbackend.model.Ship;
import com.booking.ISAbackend.service.ShipService;
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
public class ShipController {
    @Autowired
    private ShipService shipService;

    @GetMapping("getShips")
    @Transactional
    public ResponseEntity<List<ShipDTO>> getShipByShipOwnerEmail(@RequestParam String email){
        try{
            List<Ship> ships = shipService.findShipByShipOwnerEmail(email);
            List<ShipDTO> dto = new ArrayList<>();
            for(Ship ship: ships){
                ShipDTO shipDTO = new ShipDTO(ship.getId(),ship.getName(), ship.getDescription(),
                        ship.getPrice(), getPhoto(ship), ship.getNumberOfPerson(), ship.getRulesOfConduct(),
                        ship.getCancellationConditions(), ship.getType(), ship.getSize(), ship.getMotorNumber(),
                        ship.getMotorPower(), ship.getMaxSpeed(), ship.getNavigationEquipment(),
                        ship.getAdditionalEquipment());
                dto.add(shipDTO);
            }
            return ResponseEntity.ok(dto);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    private List<String> getPhoto(Ship ship){
        List<String> photos = new ArrayList<>();
        for(Photo p: ship.getPhotos()){
            photos.add(p.getPath());
        }
        return photos;
    }
}
