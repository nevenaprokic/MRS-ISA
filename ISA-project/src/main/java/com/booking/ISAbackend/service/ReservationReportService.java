package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.NewReservationReportDTO;
import com.booking.ISAbackend.dto.OfferForReportDTO;

import java.util.List;

public interface ReservationReportService {
    List<Integer> getReservationReportCottageOwner(String ownerEmail);
    List<Integer> getReservationReportShipOwner(String ownerEmail);
    void addReservationReport(NewReservationReportDTO dto);
    List<OfferForReportDTO> getReportIncomeStatementCottage(String start, String end, String email);
    List<OfferForReportDTO> getReportIncomeStatementShip(String start, String end, String email);
    List<OfferForReportDTO> getReportIncomeStatementAdventure(String start, String end, String email);
}
