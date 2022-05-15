package com.booking.ISAbackend.dto;

public class CalendarItem {
    int id;
    boolean isReservation;
    String startDate;
    String endDate;
    String title;

    public CalendarItem(int id, boolean isReservation, String startDate, String endDate, String title) {
        this.id = id;
        this.isReservation = isReservation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public boolean isReservation() {
        return isReservation;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }
}
