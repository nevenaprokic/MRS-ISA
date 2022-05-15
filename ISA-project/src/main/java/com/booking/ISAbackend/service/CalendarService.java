package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.CalendarItem;

import java.util.List;

public interface CalendarService {
    List<CalendarItem> getCalendarInfo(String ownerEmail,  int offerId);
}
