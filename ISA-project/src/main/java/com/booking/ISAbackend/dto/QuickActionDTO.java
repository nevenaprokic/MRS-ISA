package com.booking.ISAbackend.dto;

import java.time.LocalDate;
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
