package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.CottageDTO;
import com.booking.ISAbackend.dto.NewCottageDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.Cottage;
import com.booking.ISAbackend.service.CottageService;
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
@RequestMapping("/cottage")
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

    @PostMapping("addCottage")
    public ResponseEntity<String> addCottage(@RequestParam("email") String ownerEmail,
                                             @RequestParam(value = "photos", required = false) List<MultipartFile> photos,
                                             @RequestParam("offerName") String offerName,
                                             @RequestParam("price") String price,
                                             @RequestParam("description") String description,
                                             @RequestParam("street") String street,
                                             @RequestParam("city") String city,
                                             @RequestParam("state") String state,
                                             @RequestParam("rulesOfConduct") String rulesOfConduct,
                                             @RequestParam("cancelationConditions") String cancelationConditions,
                                             @RequestParam("peopleNum") String peopleNum,
                                             @RequestParam("roomNumber") String roomNumber,
                                             @RequestParam("bedNumber") String bedNumber){

        try {
            NewCottageDTO cottageDTO = new NewCottageDTO(ownerEmail, offerName, description, price, photos,
                    peopleNum, rulesOfConduct, cancelationConditions,
                    street, city, state, roomNumber, bedNumber);

            int cottageId = cottageService.addCottage(cottageDTO);
            return ResponseEntity.ok(String.valueOf(cottageId));
        } catch (InvalidPriceException | CottageAlreadyExistsException | InvalidPeopleNumberException | InvalidRoomNumberException | InvalidBedNumberException | RequiredFiledException | InvalidAddressException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Something went wrong, please try again.");
        }

    }
    @PostMapping("add-additional-services")
    public ResponseEntity<String> addAdditionalServiceForCottage(@RequestBody Map<String, Object> data){
        try{
            HashMap<String, Object>paramsMap =  (HashMap<String, Object>) data.get("params");
            int id = Integer.parseInt(paramsMap.get("offerId").toString());
            List<HashMap<String, String>> additionalServiceDTO = (List<HashMap<String, String>>) paramsMap.get("additionalServiceDTO");

            cottageService.addAdditionalServices(additionalServiceDTO, id);
            return ResponseEntity.ok().body("Successfully added new cottage");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("search-cottages-client")
    @Transactional
    public ResponseEntity<List<CottageDTO>> searchCottagesClient(@RequestParam String name, @RequestParam String description, @RequestParam String address){

        try{
            List<Cottage> cottages = cottageService.searchCottagesClient(name, description, address);
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
