package com.booking.ISAbackend.dto;

import com.booking.ISAbackend.model.AdditionalService;

public class AdditionalServiceDTO {
    private String serviceName;
    private Double servicePrice;

    public AdditionalServiceDTO(String name, Double price) {
        this.serviceName = name;
        this.servicePrice = price;
    }
    public AdditionalServiceDTO(AdditionalService a) {
        this.serviceName = a.getName();
        this.servicePrice = a.getPrice();
    }

    public AdditionalServiceDTO(){

    }

    public String getServiceName() {
        return serviceName;
    }

    public Double getServicePrice() {
        return servicePrice;
    }
}
