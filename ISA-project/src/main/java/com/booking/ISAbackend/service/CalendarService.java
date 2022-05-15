package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.CalendarItem;
import com.booking.ISAbackend.dto.ReservationDTO;

import java.io.IOException;
import java.util.List;

public interface CalendarService {
    List<CalendarItem> getCalendarInfo(String ownerEmail,  int offerId);
    ReservationDTO getReservationDetails(int reservationId) throws IOException;
}
