package com.booking.ISAbackend.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
}
