package com.booking.ISAbackend.dto;

import com.booking.ISAbackend.client.Client;
import com.booking.ISAbackend.model.*;

import javax.persistence.*;
import java.util.List;

public class ShipDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private List<String> photos;
    private Integer numberOfPerson;
    private String rulesOfConduct;
    private String cancellationConditions;

    private String type;
    private String size;
    private Integer motorNumber;
    private Integer motorPower;
    private Integer maxSpeed;
    private String navigationEquipment;
    private String additionalEquipment;


    public ShipDTO(Integer id, String name, String description, Double price, List<String> photos, Integer numberOfPerson, String rulesOfConduct, String cancellationConditions, String type, String size, Integer motorNumber, Integer motorPower, Integer maxSpeed, String navigationEquipment, String additionalEquipment) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.photos = photos;
        this.numberOfPerson = numberOfPerson;
        this.rulesOfConduct = rulesOfConduct;
        this.cancellationConditions = cancellationConditions;
        this.type = type;
        this.size = size;
        this.motorNumber = motorNumber;
        this.motorPower = motorPower;
        this.maxSpeed = maxSpeed;
        this.navigationEquipment = navigationEquipment;
        this.additionalEquipment = additionalEquipment;

    }

    public ShipDTO() {

    }
    public Integer getId() {return id;}

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

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public Integer getMotorNumber() {
        return motorNumber;
    }

    public Integer getMotorPower() {
        return motorPower;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public String getNavigationEquipment() {
        return navigationEquipment;
    }

    public String getAdditionalEquipment() {
        return additionalEquipment;
    }

}
