package com.booking.ISAbackend.dto;

import java.util.List;

public class NewCottageDTO {
    private String ownerEmail;
    private String offerName;
    private String description;
    private String price;
    private List<String> photos;
    private String peopleNum;
    private String rulesOfConduct;
    private List<AdditionalServiceDTO> additionalServices;
    private String cancelationConditions;
    private String street;
    private String city;
    private String state;
    private String roomNumber;
    private String bedNumber;

    public NewCottageDTO(String ownerEmail, String offerName, String description, String price, List<String> photos, String peopleNum, String rulesOfConduct, List<AdditionalServiceDTO> additionalServices, String cancelationConditions, String street, String city, String state, String roomNumber, String bedNumber) {
        this.ownerEmail = ownerEmail;
        this.offerName = offerName;
        this.description = description;
        this.price = price;
        this.photos = photos;
        this.peopleNum = peopleNum;
        this.rulesOfConduct = rulesOfConduct;
        this.additionalServices = additionalServices;
        this.cancelationConditions = cancelationConditions;
        this.street = street;
        this.city = city;
        this.state = state;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
    }
    public NewCottageDTO(){}

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getOfferName() {
        return offerName;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public List<String> getPhotos() {
        return photos;
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

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }
}
