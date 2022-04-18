package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.AdventureDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adventure")
public class AdventureControler {

    @Autowired
    private AdventureService adventureService;

    @PostMapping("addAdventure")

    public ResponseEntity<String> addAdventure(@RequestBody AdventureDTO adventure){
        //provera da li je ulogovan i autorizacija
        try{
            adventureService.addAdventure(adventure);
            return ResponseEntity.ok("Successfully added new adventure");
        } catch (InvalidPriceException | AdventureAlreadyExistsException | InvalidPeopleNumberException | RequiredFiledException | InvalidAddressException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }catch(Exception e) {
            return ResponseEntity.status(400).body("Something went wrong, please try again.");
        }

    }

    @GetMapping("instructor")
    public ResponseEntity<List<AdventureDTO>> getInstructorAdventures(@RequestParam String email){
        return ResponseEntity.ok(new ArrayList<AdventureDTO>());
    }
}
