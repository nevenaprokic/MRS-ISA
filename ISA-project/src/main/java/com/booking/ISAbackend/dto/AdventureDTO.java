package com.booking.ISAbackend.dto;

import com.booking.ISAbackend.model.*;

import javax.persistence.*;
import java.util.List;

public class AdventureDTO {

    private String ownerEmail;
    private String offerName;
    private String description;
    private String price;
    private List<String> pictures;
    private String peopleNum;
    private String rulesOfConduct;
    private List<AdditionalServiceDTO> additionalServices;
    private String cancelationConditions;
    private String street;
    private String city;
    private String state;
    private String additionalEquipment;

    public String getOfferName() {
        return offerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public String getRulesOfConduct() {
        return rulesOfConduct;
    }

    public List<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public String getCancelationConditions() {
        return cancelationConditions;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getAdditionalEquipment() {
        return additionalEquipment;
    }
}
