package com.booking.ISAbackend.client;

public interface ClientService {

    String save(ClientRequest c) throws InterruptedException;
    Client findByEmail(String email);
    //String confirmToken(String token);
}
