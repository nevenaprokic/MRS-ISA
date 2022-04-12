package com.booking.ISAbackend.client;

import com.booking.ISAbackend.model.*;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Client extends MyUser {

    @Enumerated(EnumType.STRING)
    private ClientCategory clientCategory;

    private Integer penal;

    @ManyToMany
    @JoinTable(name = "subscribe", joinColumns = @JoinColumn(name = "my_user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"))
    private List<Offer> subscribedOffers;

    public Client() {
        super();
    }

    public Client(Integer id, String firstName, String lastName, String password, String phoneNumber, String email, Boolean deleted, Role role, Address address, DeleteRequest deleteRequest,
                  ClientCategory clientCategory, Integer penal, List<Offer> subscribedOffers, Boolean emailVerified) {
        super(id, firstName, lastName, password, phoneNumber, email, deleted, role, address, deleteRequest, emailVerified);
        this.clientCategory = clientCategory;
        this.penal = penal;
        this.subscribedOffers = subscribedOffers;
    }

    public ClientCategory getClientCategory() {
        return clientCategory;
    }

    public Integer getPenal() {
        return penal;
    }

    public List<Offer> getSubscribedOffers() {
        return subscribedOffers;
    }

    public void setClientCategory(ClientCategory clientCategory) {
        this.clientCategory = clientCategory;
    }

    public void setPenal(Integer penal) {
        this.penal = penal;
    }

    public void setSubscribedOffers(List<Offer> subscribedOffers) {
        this.subscribedOffers = subscribedOffers;
    }
}
