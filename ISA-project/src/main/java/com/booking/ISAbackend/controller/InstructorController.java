package com.booking.ISAbackend.controller;


import com.booking.ISAbackend.dto.InstructorProfileData;
import com.booking.ISAbackend.dto.ShipDTO;
import com.booking.ISAbackend.model.Instructor;
import com.booking.ISAbackend.model.Ship;
import com.booking.ISAbackend.service.CottageService;
import com.booking.ISAbackend.service.InstructorService;
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
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping("searchInstructors")
    @Transactional
    public ResponseEntity<List<InstructorProfileData>> searchShips(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String address, @RequestParam String phoneNumber){
        try{
            List<InstructorProfileData> instructors  = instructorService.searchInstructors(firstName, lastName, address, phoneNumber);
            return ResponseEntity.ok(instructors);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getAllInstructors")
    @Transactional
    public ResponseEntity<List<InstructorProfileData>> getAll(){
        try{
            List<InstructorProfileData> instructors  = instructorService.findAll();
            return ResponseEntity.ok(instructors);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
