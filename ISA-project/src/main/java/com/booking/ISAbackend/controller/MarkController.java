package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.MarkDTO;
import com.booking.ISAbackend.model.Mark;
import com.booking.ISAbackend.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("all-unchecked")
    public ResponseEntity<List<MarkDTO>> getAllUncheckedMarks(){
        try{
            List<MarkDTO> marks = markService.getAllUncheckesMarks();
            System.out.println(marks.size());
            return new ResponseEntity<>(marks, marks.size() != 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }



}
