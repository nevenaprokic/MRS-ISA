package com.booking.ISAbackend.dto;

import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.model.Client;
import com.booking.ISAbackend.model.Offer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class ReservationDTO {
    private int id;
    private String startDate;
    private String endDate;
    private List<AdditionalServiceDTO> additionalServices;
    private double price;
    private int numberOfPerson;
    private int offerId;
    private String offerName;
    private int clinetId;
    private String clienName;
    private String offerPhoto;

    public ReservationDTO(int id, String startDate, String endDate, List<AdditionalServiceDTO> additionalServices, double price, int numberOfPerson, int offerId, String offerName, int clinetId, String clienName, String offerPhoto) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.additionalServices = additionalServices;
        this.price = price;
        this.numberOfPerson = numberOfPerson;
        this.offerId = offerId;
        this.offerName = offerName;
        this.clinetId = clinetId;
        this.clienName = clienName;
        this.offerPhoto = offerPhoto;
    }

    public int getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public double getPrice() {
        return price;
    }

    public int getNumberOfPerson() {
        return numberOfPerson;
    }

    public int getOfferId() {
        return offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public int getClinetId() {
        return clinetId;
    }

    public String getClienName() {
        return clienName;
    }

    public String getOfferPhoto() {
        return offerPhoto;
    }
}
