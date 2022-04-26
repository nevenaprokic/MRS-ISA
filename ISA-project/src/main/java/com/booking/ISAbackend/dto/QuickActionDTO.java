package com.booking.ISAbackend.dto;

import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.model.QuickReservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QuickActionDTO {
    private LocalDate startDate;
    private LocalDate endDateAction;
    private LocalDate startDateAction;
    private LocalDate endDate;
    private List<String> additionalServices;
    private Double price;
    private Integer numberOfPerson;
    public QuickActionDTO(){}
    public QuickActionDTO(LocalDate startDate, LocalDate endDateAction, LocalDate startDateAction, LocalDate endDate, List<String> additionalServices, Double price, Integer numberOfPerson) {
        this.startDate = startDate;
        this.endDateAction = endDateAction;
        this.startDateAction = startDateAction;
        this.endDate = endDate;
        this.additionalServices = additionalServices;
        this.price = price;
        this.numberOfPerson = numberOfPerson;
    }
    public QuickActionDTO(QuickReservation res) {
        this.startDate = res.getStartDate();
        this.endDateAction = res.getEndDateAction();
        this.startDateAction = res.getStartDateAction();
        this.endDate = res.getEndDate();
        this.additionalServices = getAdditionalServices(res.getAdditionalServices());
        this.price = res.getPrice();
        this.numberOfPerson = res.getNumberOfPerson();
    }
    private List<String> getAdditionalServices(List<AdditionalService> services){
        List<String> additionalServices = new ArrayList<>();
        for(AdditionalService ad: services){
            additionalServices.add(ad.getName());
        }
        return  additionalServices;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDateAction() {
        return endDateAction;
    }

    public LocalDate getStartDateAction() {
        return startDateAction;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<String> getAdditionalServices() {
        return additionalServices;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getNumberOfPerson() {
        return numberOfPerson;
    }

}
