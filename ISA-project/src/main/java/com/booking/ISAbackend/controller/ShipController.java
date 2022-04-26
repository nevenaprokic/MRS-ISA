package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.ShipDTO;
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
    public ResponseEntity<List<ShipDTO>> getShipByShipOwnerEmail(@RequestParam String email) {
        try {
            List<Ship> ships = shipService.findShipByShipOwnerEmail(email);
            List<ShipDTO> dto = new ArrayList<>();
            for (Ship ship : ships) {
                ShipDTO shipDTO = new ShipDTO(ship);
                dto.add(shipDTO);
            }
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("getShipInfo")
    @Transactional
    public ResponseEntity<ShipDTO> getShipInfo(@RequestParam String idShip){
        try{
            Ship ship = shipService.findShipById(Integer.parseInt(idShip));

            if(ship == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            ShipDTO shipDTO = new ShipDTO(ship);
            return  ResponseEntity.ok(shipDTO);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("searchShipByShipOwner")
    @Transactional
    public ResponseEntity<List<ShipDTO>> searchShipByShipOwner(@RequestParam String name, @RequestParam String address, @RequestParam Integer maxPeople, @RequestParam Double price, @RequestParam String shipOwnerUsername){
        try{
            List<Ship> ships = shipService.searchShipByShipOwner(name, maxPeople, address, price, shipOwnerUsername);
            List<ShipDTO> dto = new ArrayList<>();
            for(Ship s: ships){
                ShipDTO shipDTO = new ShipDTO(s);
                dto.add(shipDTO);
            }
            return ResponseEntity.ok(dto);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }



}
