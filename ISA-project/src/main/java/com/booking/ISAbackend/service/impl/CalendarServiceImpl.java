package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.CalendarItem;
import com.booking.ISAbackend.dto.ReservationDTO;
import com.booking.ISAbackend.dto.UnavailableDateDTO;
import com.booking.ISAbackend.exceptions.BusyDateIntervalException;
import com.booking.ISAbackend.exceptions.InvalidDateInterval;
import com.booking.ISAbackend.exceptions.UnavailableDatesAlreadyDefine;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.OfferRepository;
import com.booking.ISAbackend.repository.ReservationRepository;
import com.booking.ISAbackend.repository.UnavailabelOfferDatesRepository;
import com.booking.ISAbackend.repository.UserRepository;
import com.booking.ISAbackend.service.AdditionalServiceService;
import com.booking.ISAbackend.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarServiceImpl implements CalendarService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UnavailabelOfferDatesRepository unavailabelOfferDatesRepository;

    @Autowired
    AdditionalServiceService additionalServiceService;

    @Autowired
    OfferRepository offerRepository;

    @Override
    @Transactional
    public List<CalendarItem> getCalendarInfo(String ownerEmail, int offerId) {
        MyUser user = userRepository.findByEmail(ownerEmail);
        int id = user.getId();
        List<Reservation> reservations = reservationRepository.findAllByOfferId(offerId);
        List<UnavailableOfferDates> unavailableOfferDates = unavailabelOfferDatesRepository.findByOfferId(offerId);

        return generateCalendarItems(reservations, unavailableOfferDates);
    }

    @Override
    @Transactional
    public ReservationDTO getReservationDetails(int reservationId) throws IOException {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            Reservation r = reservation.get();
            Offer offer = r.getOffer();
            Client client = r.getClient();
            String clientName = client.getFirstName() + " " + client.getLastName();
            String offerPhoto = getOfferPhoto(offer);

            ReservationDTO reservationDTO = new ReservationDTO(r.getId(),
                    localDateToString(r.getStartDate()),
                    localDateToString(r.getEndDate()),
                    additionalServiceService.getAdditionalServices(offer),
                    r.getPrice(),
                    r.getNumberOfPerson(),
                    offer.getId(),
                    offer.getName(),
                    client.getId(),
                    clientName,
                    offerPhoto
                    );
            return  reservationDTO;
        }
        return null;

    }
    @Override
    public void addOffersUnavailableDates(UnavailableDateDTO unavailableDates) throws BusyDateIntervalException, InvalidDateInterval, UnavailableDatesAlreadyDefine {
        Offer offer = offerRepository.getById(unavailableDates.getOfferId());
        LocalDate start = revertToDate(unavailableDates.getStartDate());
        LocalDate end = revertToDate(unavailableDates.getEndDate());
        List<Reservation> reservationsInSelectedInterval = reservationRepository.findByOfferIdAndDates(start, end);
        if (!reservationsInSelectedInterval.isEmpty()) throw new BusyDateIntervalException();
        if(!checkDateInterval(start, end)) throw  new InvalidDateInterval();
        List<UnavailableOfferDates> exitingUnavailableDatesInInterval = unavailabelOfferDatesRepository.findDatesByOfferInInterval(start, end,unavailableDates.getOfferId());
        if(!exitingUnavailableDatesInInterval.isEmpty()) throw  new UnavailableDatesAlreadyDefine();
        UnavailableOfferDates dates = new UnavailableOfferDates(offer, start, end);
        unavailabelOfferDatesRepository.save(dates);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return formatter.format(date);
    }

    private String getOfferPhoto(Offer offer) throws IOException {
        String photoName = "no-image.png";
        if(!offer.getPhotos().isEmpty()) {
            photoName = offer.getPhotos().get(0).getPath();
        }
        return convertPhoto(photoName);
    }

    private String convertPhoto(String photoName) throws IOException {
        String pathFile = "./src/main/frontend/src/components/images/" + photoName;
        byte[] bytes = Files.readAllBytes(Paths.get(pathFile));
        String photoData = Base64.getEncoder().encodeToString(bytes);
        return photoData;
    }

    private LocalDate revertToDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    private boolean checkDateInterval(LocalDate start, LocalDate end){
        if(start == end) return true;
        return end.isAfter(start);
    }

}
