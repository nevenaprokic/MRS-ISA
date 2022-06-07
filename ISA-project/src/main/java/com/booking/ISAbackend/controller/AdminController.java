package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.AdminDTO;
import com.booking.ISAbackend.dto.UserProfileData;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("profile-info")
    public ResponseEntity<AdminDTO> getAdminProfileInfo(@RequestParam String email){
        try{
            AdminDTO adminData = userService.findAdminByEmail(email);
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

    @PostMapping("add-admin")
    public ResponseEntity<String> addNewAdmin (@RequestBody UserProfileData newAdminData){
        try{
                userService.addNewAdmin(newAdminData);
                return ResponseEntity.ok().body("Successfully added new admin.");
        } catch (OnlyLettersAndSpacesException | InvalidPhoneNumberException | InvalidAddressException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (AlreadyExitingUsernameException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Something went wrong, please try agan later.");
        }

    }

    @PostMapping("change-password/{email}")
    public ResponseEntity<String> changeFirstLoginPassword(@PathVariable String email, @RequestBody HashMap<String, String> data){
        try {
            userService.cahngeAdminFirstPassword(email, data);
            return ResponseEntity.ok("Successfully changed password.");
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(400).body("Old password is not correct.");
        }
        catch (Exception e){
            return ResponseEntity.status(400).body("Something wnt wrong. Please try again later");
        }
    }
}
