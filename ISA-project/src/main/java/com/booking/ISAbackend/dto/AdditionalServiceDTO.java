package com.booking.ISAbackend.dto;

public class AdditionalServiceDTO {
    private String serviceName;
    private String servicePrice;

    public AdditionalServiceDTO(String name, String price) {
        serviceName = name;
        servicePrice = price;
    }

    public AdditionalServiceDTO(){

    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServicePrice() {
        return servicePrice;
    }
}
