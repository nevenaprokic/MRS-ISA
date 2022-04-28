package com.booking.ISAbackend.dto;;

import com.booking.ISAbackend.model.Cottage;
import com.booking.ISAbackend.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class CottageDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private List<String> photos;
    private Integer numberOfPerson;
    private String rulesOfConduct;
    private String cancellationConditions;
    private Integer roomNumber;
    private Integer bedNumber;


    public CottageDTO(Integer id,String name, String description, Double price, List<String> photos, Integer numberOfPerson, String rulesOfConduct, String cancellationConditions, Integer roomNumber, Integer bedNumber) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.photos = photos;
        this.numberOfPerson = numberOfPerson;
        this.rulesOfConduct = rulesOfConduct;
        this.cancellationConditions = cancellationConditions;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
    }

    public CottageDTO() {

    }

    public CottageDTO(Cottage c) {
        this.id = c.getId();
        this.name = c.getName();
        this.description = c.getDescription();
        this.price = c.getPrice();
        this.photos = getPhoto(c);
        this.numberOfPerson = c.getNumberOfPerson();
        this.rulesOfConduct = c.getRulesOfConduct();
        this.cancellationConditions = c.getCancellationConditions();
        this.roomNumber = c.getRoomNumber();
        this.bedNumber = c.getBedNumber();
    }

    private List<String> getPhoto(Cottage c){
        List<String> photos = new ArrayList<>();
        for(Photo p: c.getPhotos()){
            photos.add(p.getPath());
        }
        return photos;
    }

    public Integer getId() {return  id;}

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

    public Integer getRoomNumber(){return  roomNumber;}

    public Integer getBedNumber() {return  bedNumber;}

}
