package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.CottageDTO;
import com.booking.ISAbackend.dto.OfferSearchParamsDTO;
import com.booking.ISAbackend.dto.NewShipDTO;
import com.booking.ISAbackend.dto.ShipDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ship")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @GetMapping("get-all-by-owner")
    public ResponseEntity<List<ShipDTO>> getShipByShipOwnerEmail(@RequestParam String email) {
        try {
            List<ShipDTO> ships = shipService.findShipByShipOwnerEmail(email);

            return ResponseEntity.ok(ships);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("get-info")
    public ResponseEntity<ShipDTO> getShipInfo(@RequestParam String idShip){
        try{
            ShipDTO ship = shipService.findShipById(Integer.parseInt(idShip));

            if(ship == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            return  ResponseEntity.ok(ship);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("search-by-owner")
    public ResponseEntity<List<ShipDTO>> searchShipByShipOwner(@RequestParam String name, @RequestParam String address, @RequestParam Integer maxPeople, @RequestParam Double price, @RequestParam String shipOwnerUsername){
        try{
            List<ShipDTO> ships = shipService.searchShipByShipOwner(name, maxPeople, address, price, shipOwnerUsername);

            return ResponseEntity.ok(ships);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("add")
    public ResponseEntity<String> addShip(@RequestParam("email") String ownerEmail,
                                          @RequestParam(value = "photos", required = false) List<MultipartFile> photos,
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

    @GetMapping("get-all")
    public ResponseEntity<List<ShipDTO>> getShips(){
        try{
            List<ShipDTO> ships = shipService.findAll();

            return ResponseEntity.ok(ships);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("search")
    public ResponseEntity<List<ShipDTO>> searchShips(@RequestParam String name, @RequestParam String address, @RequestParam Integer maxPeople, @RequestParam Double price){

        try{
            List<ShipDTO> ships = shipService.searchShips(name, maxPeople, address, price);
            return ResponseEntity.ok(ships);
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

    @PostMapping("search-client")
    public ResponseEntity<List<ShipDTO>> searchShipsClient(@RequestBody OfferSearchParamsDTO params){
        try{
            List<ShipDTO> ships = shipService.searchShipsClient(params);
            return ResponseEntity.ok(ships);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
