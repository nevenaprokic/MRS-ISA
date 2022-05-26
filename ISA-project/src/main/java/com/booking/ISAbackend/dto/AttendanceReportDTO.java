package com.booking.ISAbackend.dto;

import java.util.List;

public class AttendanceReportDTO {
    private String offerName;
    private List<Integer> value;

    public AttendanceReportDTO(String offerName, List<Integer> value) {
        this.offerName = offerName;
        this.value = value;
    }
    public AttendanceReportDTO(){}

    public String getOfferName() {
        return offerName;
    }

    public List<Integer> getValue() {
        return value;
    }
}
