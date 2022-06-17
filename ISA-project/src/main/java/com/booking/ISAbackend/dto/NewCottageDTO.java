package com.booking.ISAbackend.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class NewCottageDTO {
    private String ownerEmail;
    private String offerName;
    private String description;
    private String price;
    private List<MultipartFile> photos;
    private String peopleNum;
    private String rulesOfConduct;
    private String cancelationConditions;
    private String street;
    private String city;
    private String state;
    private String roomNumber;
    private String bedNumber;

    public NewCottageDTO(String ownerEmail, String offerName, String description, String price, List<MultipartFile> photos, String peopleNum, String rulesOfConduct, String cancelationConditions, String street, String city, String state, String roomNumber, String bedNumber) {
        this.ownerEmail = ownerEmail;
        this.offerName = offerName;
        this.description = description;
        this.price = price;
        this.photos = photos;
        this.peopleNum = peopleNum;
        this.rulesOfConduct = rulesOfConduct;
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

    public List<MultipartFile> getPhotos() {
        return photos;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public String getRulesOfConduct() {
        return rulesOfConduct;
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

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPhotos(List<MultipartFile> photos) {
        this.photos = photos;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public void setRulesOfConduct(String rulesOfConduct) {
        this.rulesOfConduct = rulesOfConduct;
    }

    public void setCancelationConditions(String cancelationConditions) {
        this.cancelationConditions = cancelationConditions;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }
}
