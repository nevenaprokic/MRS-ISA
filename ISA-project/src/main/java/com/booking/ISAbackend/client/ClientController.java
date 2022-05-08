package com.booking.ISAbackend.client;

import com.booking.ISAbackend.exceptions.AccountDeletionException;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Locale;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("auth/client/registration")
    public ResponseEntity<String> addClient(@RequestBody ClientRequest request, UriComponentsBuilder ucBuilder) throws InterruptedException {

        Client existUser = this.clientService.findByEmail(request.getEmail());

        if (existUser != null) {
            return ResponseEntity.status(400).body("Email is already registered.");
        }

        String token = this.clientService.save(request);

        return ResponseEntity.ok("Registration was successful.");
    }

    @GetMapping("clientProfileInfo")
    @Transactional
    public ResponseEntity<ClientDTO> getInstructorProfileInfo(@RequestParam String email){

        Client client = clientService.findByEmail(email);
        ClientDTO dto = new ClientDTO(
            client.getEmail(),
                client.getFirstName(),
                client.getLastName(),
                client.getPhoneNumber(),
                client.getAddress().getStreet(),
                client.getAddress().getCity(),
                client.getAddress().getState(),
                client.getClientCategory().toString(),
                client.getPenal()
        );

        return ResponseEntity.ok(dto);
    }

    @PostMapping("updateProfileInfo")
    @Transactional
    public ResponseEntity<String> updateInfo(@RequestParam String email, @RequestBody ClientDTO dto) {
        try{
            clientService.updateInfo(email, dto);
            return ResponseEntity.ok("Successfully updated personal info");
        } catch (OnlyLettersAndSpacesException | InvalidPhoneNumberException | InvalidAddressException e) {

            return ResponseEntity.status(400).body("Data is invalid.");
        }
    }

    @PostMapping("deleteAccount")
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


}
