package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.UserProfileData;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;
import com.booking.ISAbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("profile-info")
    public ResponseEntity<UserProfileData> getAdminProfileInfo(@RequestParam String email){
        try{
            UserProfileData adminData = userService.findAdminByEmail(email);
            return ResponseEntity.ok(adminData);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("change-data")
    public ResponseEntity<String> changeAdminData(@RequestBody UserProfileData newData){
        try{
            userService.changeAdminData(newData);
            return ResponseEntity.ok("Successfully changed you data");
        } catch (OnlyLettersAndSpacesException | InvalidPhoneNumberException | InvalidAddressException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
