package com.booking.ISAbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CottageSearchParamsDTO {

    private String name;
    private String description;
    private String address;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateFrom;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateTo;

    public CottageSearchParamsDTO(){}

    public CottageSearchParamsDTO(String name, String description, String address, LocalDate dateFrom, LocalDate dateTo) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
