package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MarkController {
    @Autowired
    private MarkService markService;

    @GetMapping("getMark")
    public ResponseEntity<Double> getMark(@RequestParam String id){
        return ResponseEntity.ok(markService.getMark(Integer.parseInt(id)));
    }
}
