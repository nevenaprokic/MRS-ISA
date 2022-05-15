package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.CalendarItem;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.ReservationRepository;
import com.booking.ISAbackend.repository.UnavailabelOfferDatesRepository;
import com.booking.ISAbackend.repository.UserRepository;
import com.booking.ISAbackend.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UnavailabelOfferDatesRepository unavailabelOfferDatesRepository;

    @Override
    @Transactional
    public List<CalendarItem> getCalendarInfo(String ownerEmail, int offerId) {
        MyUser user = userRepository.findByEmail(ownerEmail);
        int id = user.getId();
        List<Reservation> reservations = reservationRepository.findAllByOfferId(offerId);
        List<UnavailableOfferDates> unavailableOfferDates = unavailabelOfferDatesRepository.findByOfferId(offerId);

        return generateCalendarItems(reservations, unavailableOfferDates);
    }

    @Transactional
    public List<CalendarItem> generateCalendarItems(List<Reservation> reservations, List<UnavailableOfferDates> unavailableOfferDates){
        List<CalendarItem> calendarItems = new ArrayList<CalendarItem>();
        //int id, boolean isReservation, String startDate, String endDate, String title

        for(Reservation reservation : reservations){
            Offer of = reservation.getOffer();
            String title = "Reservation: " + of.getName();
            CalendarItem item = new CalendarItem(reservation.getId(), true, reservation.getStartDate().toString(), reservation.getEndDate().toString(), title);
            calendarItems.add(item);
        }

        for(UnavailableOfferDates date : unavailableOfferDates){
            String title = "Unavailable";
            CalendarItem item = new CalendarItem(date.getId(), false, date.getStartDate().toString(), date.getEndDate().toString(), title);
            calendarItems.add(item);
        }
        return calendarItems;
    }

    private String localDateToString(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-mm-dd");
        return formatter.format(date);
    }



}
