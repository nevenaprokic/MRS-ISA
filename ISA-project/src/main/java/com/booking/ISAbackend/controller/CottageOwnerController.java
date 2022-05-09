package com.booking.ISAbackend.controller;


import com.booking.ISAbackend.dto.CottageOwnerNewDataDTO;
import com.booking.ISAbackend.dto.CottageOwnerProfileInfoDTO;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;
import com.booking.ISAbackend.service.RegistrationRequestService;
import com.booking.ISAbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("cottage-owner")
public class CottageOwnerController {

    @Autowired
    private UserService userService;
    @Autowired
    private RegistrationRequestService registrationRequestService;

    @GetMapping("profile-info")
    public ResponseEntity<CottageOwnerProfileInfoDTO> getCottageOwnerProfileInfo(@RequestParam String email){
        try{
            CottageOwnerProfileInfoDTO cottageOwner =  userService.getCottageOwnerDataByEmail(email);
            return ResponseEntity.ok(cottageOwner);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("change-data")
    public ResponseEntity<String> changeCottageOwnerData(@RequestBody CottageOwnerNewDataDTO newData){
        try{
            userService.changeCottageOwnerData(newData);
            return ResponseEntity.ok("Successfully changed your data");
        } catch (OnlyLettersAndSpacesException | InvalidPhoneNumberException | InvalidAddressException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("send-delete-request")
    public ResponseEntity<String> sendDeleteRequestCottageOwner(@RequestParam String email, @RequestBody HashMap<String, String> data) {
        try{
            if(userService.sendDeleteRequestCottageOwner(email, data.get("reason")))
                return ResponseEntity.ok("Successfully created request to delete the order.");
            else
                return ResponseEntity.status(400).body("Unable to send request to delete the order, user's offers have reservations.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Unable to send request to delete the order.");
        }
    }

}
