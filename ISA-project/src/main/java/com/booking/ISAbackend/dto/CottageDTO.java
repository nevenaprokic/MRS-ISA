package com.booking.ISAbackend.dto;

import com.booking.ISAbackend.client.Client;
import com.booking.ISAbackend.model.*;

import java.util.List;

public class CottageDTO {
    private String name;
    private String description;
    private Double price;
    private List<String> photos;
    private Integer numberOfPerson;
    private String rulesOfConduct;
    private String cancellationConditions;
    
    private Integer roomNumber;
    private Integer bedNumber;



    public CottageDTO(String name, String description, Double price, List<String> photos, Integer numberOfPerson, String rulesOfConduct, String cancellationConditions) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.photos = photos;
        this.numberOfPerson = numberOfPerson;
        this.rulesOfConduct = rulesOfConduct;
        this.cancellationConditions = cancellationConditions;
    }

    public CottageDTO() {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public Integer getNumberOfPerson() {
        return numberOfPerson;
    }

    public String getRulesOfConduct() {
        return rulesOfConduct;
    }

    public String getCancellationConditions() {
        return cancellationConditions;
    }

}
