package com.booking.ISAbackend.dto;

import java.util.List;

public class AdventureDetailsDTO extends AdventureDTO{
    public int id;

    public AdventureDetailsDTO(String ownerEmail, String offerName, String description, String price, List<String> pictures, String peopleNum, String rulesOfConduct, List<AdditionalServiceDTO> additionalServices, String cancelationConditions, String street, String city, String state, String additionalEquipment, int id) {
        super(ownerEmail, offerName, description, price, pictures, peopleNum, rulesOfConduct, additionalServices, cancelationConditions, street, city, state, additionalEquipment);
        this.id = id;
    }

    public AdventureDetailsDTO(){

    }


}
