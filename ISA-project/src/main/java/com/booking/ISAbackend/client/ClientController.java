package com.booking.ISAbackend.client;

import com.booking.ISAbackend.dto.InstructorProfileData;
import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Locale;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("auth/client/registration")
    public ResponseEntity<String> addClient(@RequestBody ClientRequest request, UriComponentsBuilder ucBuilder) throws InterruptedException {

        Client existUser = this.clientService.findByEmail(request.getEmail());

        if (existUser != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            //throw new ResourceConflictException(request.getId(), "Username already exists");
        }

        String token = this.clientService.save(request);

        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @GetMapping("clientProfileInfo")
    //@Transactional
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

}
