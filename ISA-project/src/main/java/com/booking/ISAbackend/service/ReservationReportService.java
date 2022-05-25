package com.booking.ISAbackend.service;

import java.util.List;

public interface ReservationReportService {
    List<Integer> getReservationReportCottageOwner(String ownerEmail);
}
