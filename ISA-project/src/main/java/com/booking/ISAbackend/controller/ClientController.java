package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.ClientDTO;
import com.booking.ISAbackend.dto.ClientRequest;
import com.booking.ISAbackend.service.ClientService;
import com.booking.ISAbackend.exceptions.AccountDeletionException;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;
import com.booking.ISAbackend.model.Client;
import com.booking.ISAbackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ReservationService reservationService;

    @PostMapping("registration")
    public ResponseEntity<String> addClient(@RequestBody ClientRequest request, UriComponentsBuilder ucBuilder) throws InterruptedException {

        ClientDTO existUser = this.clientService.findByEmail(request.getEmail());

        if (existUser != null) {
            return ResponseEntity.status(400).body("Email is already registered.");
        }

        String token = this.clientService.save(request);

        return ResponseEntity.ok("Registration was successful.");
    }

    @GetMapping("profile-info")
    public ResponseEntity<ClientDTO> getClientProfileInfo(@RequestParam String email){
        ClientDTO dto = clientService.findByEmail(email);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("update-profile-info")
    public ResponseEntity<String> updateInfo(@RequestParam String email, @RequestBody ClientDTO dto) {
        try{
            clientService.updateInfo(email, dto);
            return ResponseEntity.ok("Successfully updated personal info");
        } catch (OnlyLettersAndSpacesException | InvalidPhoneNumberException | InvalidAddressException e) {

            return ResponseEntity.status(400).body("Data is invalid.");
        }
    }

    @PostMapping("delete-account")
    public ResponseEntity<String> deleteAccount(@RequestBody HashMap<String, String> data){
        try{
            clientService.requestAccountDeletion(data.get("email"), data.get("reason"));
            return ResponseEntity.ok("Successfully created request for deleting account.");
        } catch (AccountDeletionException e) {
            return ResponseEntity.status(400).body("Account cannot be deleted or request already exist.");
        }
    }

    @GetMapping("deletion-requested")
    public ResponseEntity<Boolean> alreadyRequestedDeletion(@RequestParam String email){
        return ResponseEntity.ok(clientService.alreadyRequestedDeletion(email));
    }

    @GetMapping("get-by-reservation")
    public ResponseEntity<List<ClientDTO>> getClientByCottageOwnerEmail(@RequestParam String ownerEmail){
        try{
            List<ClientDTO> clients = reservationService.getClientByCottageOwnerEmail(ownerEmail);
//            clientService.requestAccountDeletion(data.get("email"), data.get("reason"));
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }


}
