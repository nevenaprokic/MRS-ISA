package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.model.ClientCategory;
import com.booking.ISAbackend.model.OwnerCategory;
import com.booking.ISAbackend.service.ClientCategoryService;
import com.booking.ISAbackend.service.OwnerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("loyalty")
public class LoyaltyProgramController {

    @Autowired
    OwnerCategoryService ownerCategoryService;

    @Autowired
    ClientCategoryService clientCategoryService;

    @GetMapping("owner-categories")
    public ResponseEntity<List<OwnerCategory>> getAllOwnerCategories(){
        try{
            return ResponseEntity.ok(ownerCategoryService.findAll());
        }catch (Exception e){
            e.printStackTrace();;
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("client-categories")
    public ResponseEntity<List<ClientCategory>> getAllCLinetCategories(){
        try{
            return ResponseEntity.ok(clientCategoryService.findAll());
        }catch (Exception e){
            e.printStackTrace();;
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
