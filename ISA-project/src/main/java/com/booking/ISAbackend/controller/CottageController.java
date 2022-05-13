package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.CottageDTO;
import com.booking.ISAbackend.dto.OfferSearchParamsDTO;
import com.booking.ISAbackend.dto.NewCottageDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cottage")
public class CottageController {
    @Autowired
    private CottageService cottageService;

    @GetMapping("get-cottages-by-owner-email")
    public ResponseEntity<List<CottageDTO>> getCottageByCottageOwnerEmail(@RequestParam String email){
        try{
            List<CottageDTO> cottages = cottageService.findCottageByCottageOwnerEmail(email);
            return ResponseEntity.ok(cottages);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-info")
    public ResponseEntity<CottageDTO> getCottageInfo(@RequestParam String idCottage){
        try{
            CottageDTO cottage = cottageService.findCottageById(Integer.parseInt(idCottage));

            if(cottage == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            return  ResponseEntity.ok(cottage);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("get-all")
    public ResponseEntity<List<CottageDTO>> getCottages(){
        try{
            List<CottageDTO> cottages = cottageService.findAll();
            return ResponseEntity.ok(cottages);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("search")
    public ResponseEntity<List<CottageDTO>> searchCottages(@RequestParam String name, @RequestParam String address, @RequestParam Integer maxPeople, @RequestParam Double price){

        try{
            List<CottageDTO> cottages = cottageService.searchCottages(name, maxPeople, address, price);
            return ResponseEntity.ok(cottages);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("search-by-owner")
    public ResponseEntity<List<CottageDTO>> searchCottagesByCottageOwner(@RequestParam String name, @RequestParam String address, @RequestParam Integer maxPeople, @RequestParam Double price, @RequestParam String cottageOwnerUsername){
        try{
            List<CottageDTO> cottages = cottageService.searchCottagesByCottageOwner(name, maxPeople, address, price, cottageOwnerUsername);
            return ResponseEntity.ok(cottages);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("add")
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

    @PostMapping("search-client")
    public ResponseEntity<List<CottageDTO>> searchCottagesClient(@RequestBody OfferSearchParamsDTO params){
        try{
            List<CottageDTO> cottages = cottageService.searchCottagesClient(params);
            return ResponseEntity.ok(cottages);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("allowed-operation")
    public ResponseEntity<Boolean> isAllowedCottageOperation(@RequestParam int cottageId){
        try{
            Boolean allowedOperation = cottageService.checkOperationAllowed(cottageId);
            return ResponseEntity.ok(allowedOperation);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}