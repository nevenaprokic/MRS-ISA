package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.NewReservationReportDTO;

import java.util.List;

public interface ReservationReportService {
    List<Integer> getReservationReportCottageOwner(String ownerEmail);
    void addReservationReport(NewReservationReportDTO dto);
}