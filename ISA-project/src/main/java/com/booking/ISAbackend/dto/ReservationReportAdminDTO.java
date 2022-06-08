package com.booking.ISAbackend.dto;

import com.booking.ISAbackend.model.Client;
import com.booking.ISAbackend.model.Reservation;
import com.booking.ISAbackend.model.ReservationReport;

import java.time.LocalDate;

public class ReservationReportAdminDTO {
    private String comment;
    private int reportId;
    private String clientName;
    private int clientId;
    private String offerName;
    private String reservationStartDate;
    private String reservationEndDate;
    private String reportSentDate;

    public ReservationReportAdminDTO(String comment, int reportId, String clientName, String offerName,
                                     String reservationStartDate, String reservationEndDate, String reportSentDate,
                                      int clientId ) {
        this.comment = comment;
        this.reportId = reportId;
        this.clientName = clientName;
        this.offerName = offerName;
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.reportSentDate = reportSentDate;
        this.clientId = clientId;
    }


    public String getComment() {
        return comment;
    }

    public int getReportId() {
        return reportId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getOfferName() {
        return offerName;
    }

    public String getReservationStartDate() {
        return reservationStartDate;
    }

    public String getReservationEndDate() {
        return reservationEndDate;
    }

    public String getReportSentDate() {
        return reportSentDate;
    }

    public int getClientId(){
        return clientId;
    }
}
