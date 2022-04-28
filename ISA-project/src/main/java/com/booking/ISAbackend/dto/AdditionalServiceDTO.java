package com.booking.ISAbackend.dto;

public class AdditionalServiceDTO {
    private String serviceName;
    private String servicePrice;
    private String offerId;

    public AdditionalServiceDTO(String name, String price) {
        serviceName = name;
        servicePrice = price;
    }
    public AdditionalServiceDTO(String name, String price, String offerId) {
        serviceName = name;
        servicePrice = price;
        this.offerId = offerId;
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
