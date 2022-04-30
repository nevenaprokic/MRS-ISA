package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.NewShipDTO;
import com.booking.ISAbackend.dto.ShipDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.Ship;
import com.booking.ISAbackend.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ship")
public class ShipController {
    //provera da li je ulogovan i autorizacija
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
    @PostMapping("addShip")
    public ResponseEntity<String> addShip(@RequestParam("email") String ownerEmail,
                                          @RequestParam("photos") List<MultipartFile> photos,
                                          @RequestParam("offerName") String offerName,
                                          @RequestParam("price") String price,
                                          @RequestParam("description") String description,
                                          @RequestParam("street") String street,
                                          @RequestParam("city") String city,
                                          @RequestParam("state") String state,
                                          @RequestParam("rulesOfConduct") String rulesOfConduct,
                                          @RequestParam("cancelationConditions") String cancelationConditions,
                                          @RequestParam("additionalEquipment") String additionalEquipment,
                                          @RequestParam("peopleNum") String peopleNum,
                                          @RequestParam("type") String type,
                                          @RequestParam("size") String size,
                                          @RequestParam("motorNumber") String motorNumber,
                                          @RequestParam("motorPower") String motorPower,
                                          @RequestParam("maxSpeed") String maxSpeed,
                                          @RequestParam("navigationEquipment") String navigationEquipment) {
        try {
            NewShipDTO shipDTO = new NewShipDTO(ownerEmail, offerName, description, price, photos, peopleNum,
                    rulesOfConduct, cancelationConditions, street, city, state, type, size, motorNumber,
                    motorPower, maxSpeed, additionalEquipment, navigationEquipment);
            int shipId = shipService.addShip(shipDTO);
            return ResponseEntity.ok(String.valueOf(shipId));
        } catch (ShipAlreadyExistsException | InvalidPriceException | InvalidAddressException | InvalidPeopleNumberException | InvalidSizeException | InvalidMotorNumberException | InvalidMotorPowerException | InvalidMaxSpeedException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Something went wrong, please try again.");
        }
    }

    @GetMapping("getAllShips")
    @Transactional
    public ResponseEntity<List<ShipDTO>> getShips(){
        try{
            List<Ship> ships = shipService.findAll();
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

    @GetMapping("searchShips")
    @Transactional
    public ResponseEntity<List<ShipDTO>> searchShips(@RequestParam String name, @RequestParam String address, @RequestParam Integer maxPeople, @RequestParam Double price){

        try{
            List<Ship> ships = shipService.searchShips(name, maxPeople, address, price);
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
    @PostMapping("add-additional-services")
    public ResponseEntity<String> addAdditionalServiceForShip(@RequestBody Map<String, Object> data){
        try{
            HashMap<String, Object> paramsMap =  (HashMap<String, Object>) data.get("params");
            int id = Integer.parseInt(paramsMap.get("offerId").toString());
            List<HashMap<String, String>> additionalServiceDTO = (List<HashMap<String, String>>) paramsMap.get("additionalServiceDTO");

            shipService.addAdditionalServices(additionalServiceDTO, id);
            return ResponseEntity.ok().body("Successfully added new ship");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
