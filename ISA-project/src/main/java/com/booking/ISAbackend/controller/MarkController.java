package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mark")
public class MarkController {
    @Autowired
    private MarkService markService;

    @GetMapping("get")
    public ResponseEntity<Double> getMark(@RequestParam String id){
        return ResponseEntity.ok(markService.getMark(Integer.parseInt(id)));
    }

    @GetMapping("get-all-cottage")
    public ResponseEntity<Double> getMarkByCottageOwnerEmail(@RequestParam String email){
        return ResponseEntity.ok(markService.getMarkByCottageOwnerEmail(email));
    }

    @GetMapping("get-all-ship")
    public ResponseEntity<Double> getMarkShipOwnerEmail(@RequestParam String email){
        return ResponseEntity.ok(markService.getMarkByShipOwnerEmail(email));
    }

    @GetMapping("get-all-adventure")
    public ResponseEntity<Double> getMarkInstructorEmail(@RequestParam String email){
        return ResponseEntity.ok(markService.getMarkByInstructorEmail(email));
    }


}
