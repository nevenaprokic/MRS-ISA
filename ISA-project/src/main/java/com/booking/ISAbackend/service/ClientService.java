package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.ClientDTO;
import com.booking.ISAbackend.dto.ClientRequest;
import com.booking.ISAbackend.dto.OfferDTO;
import com.booking.ISAbackend.exceptions.AccountDeletionException;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;
import com.booking.ISAbackend.model.Client;

import java.io.IOException;
import java.util.List;

public interface ClientService {

    String save(ClientRequest c) throws InterruptedException;
    ClientDTO findByEmail(String email);
    void updateInfo(String email, ClientDTO dto) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException;
    void requestAccountDeletion(String email, String reason) throws AccountDeletionException;
    boolean alreadyRequestedDeletion(String email);
    void removeSubscribedClients(List<Client> services);
    Boolean canReserve(String email);

    void makeReview(Integer stars, Integer offerId, String comment) throws Exception;

    List<OfferDTO> getSubscriptions(String email) throws IOException;

    void unsubscribe(String email, String offerId);

    void subscribe(String email, String offerId);

    Boolean isSubscribed(String email, String offerId);
}
