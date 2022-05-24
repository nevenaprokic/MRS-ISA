package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.*;
import com.booking.ISAbackend.email.EmailSender;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.*;
import com.booking.ISAbackend.service.AdditionalServiceService;
import com.booking.ISAbackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private AdditionalServiceService additionalServiceService;
    @Autowired
    private QuickReservationRepository quickReservationRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private ClientCategoryRepository clientCategoryRepository;

    @Override
    @Transactional
    public void makeReservation(ReservationParamsDTO params) throws OfferNotAvailableException, PreviouslyCanceledReservationException, ClientNotAvailableException {
        Optional<Integer> isCanceled = reservationRepository.checkIfCanceled(params.getEmail(), params.getDate(), params.getOfferId());
        if(isCanceled.isPresent())
            throw new PreviouslyCanceledReservationException("Reservation has already been reserved and canceled.");
        if(!isAvailableClient(params.getEmail(), params.getDate().toString(), params.getEndingDate().toString()))
            throw new ClientNotAvailableException("Client is not available in this time period.");

        List<Optional<AdditionalService>> services = new ArrayList<>();
        for(AdditionalService s : params.getServices()){
            services.add(additionalServiceRepository.findById(s.getId()));
        }
        Client user = clientRepository.findByEmail(params.getEmail());
        Offer offer = offerRepository.findOfferById(params.getOfferId());
        List<Offer> nonAvailable = offerRepository.nonAvailableOffers(params.getDate(), params.getEndingDate());

        for(Offer o: nonAvailable){
            if(Objects.equals(o.getId(), offer.getId()))
                throw new OfferNotAvailableException("Offer is not available in that period.");
        }

        for(UnavailableOfferDates ofd : offer.getUnavailableDate()){
            // (StartA <= EndB) and (EndA >= StartB)
            if(ofd.getEndDate().isAfter(params.getDate()) && ofd.getStartDate().isBefore(params.getEndingDate()))
                throw new OfferNotAvailableException("Offer is not available in that period.");
        }

        if(params.getActionId() != null)
            quickReservationRepository.deleteById(params.getActionId());

        List<AdditionalService> ys = new ArrayList<>();
        for (Optional<AdditionalService> x : services) {
            x.ifPresent(ys::add);
        }

        Reservation r = new Reservation(params.getDate(), params.getEndingDate(), ys, params.getTotal(), params.getGuests(), offer, user, false);
        reservationRepository.save(r);
        emailSender.reservationConfirmation(params);
    }

    @Override
    @Transactional
    public List<ReservationDTO> getPastCottageReservationsByClient(String email) throws IOException {
        List<Reservation> reservations = reservationRepository.getPastCottageReservationsByClient(email, LocalDate.now());
        return getReservationDTOS(reservations);
    }

    @Override
    @Transactional
    public List<ReservationDTO> getPastShipReservationsByClient(String email) throws IOException {
        List<Reservation> reservations = reservationRepository.getPastShipReservationsByClient(email, LocalDate.now());
        return getReservationDTOS(reservations);
    }

    @Override
    @Transactional
    public List<ReservationDTO> getPastAdventureReservationsByClient(String email) throws IOException {
        List<Reservation> reservations = reservationRepository.getPastAdventureReservationsByClient(email, LocalDate.now());
        return getReservationDTOS(reservations);
    }

    @Override
    @Transactional
    public Boolean isAvailableOffer(Integer offerId, String startDate, Integer dayNum) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDateReservation = LocalDate.parse(startDate, formatter);
        LocalDate endDateReservation = startDateReservation.plusDays(dayNum);
        List<Reservation> reservations = reservationRepository.findAllByOfferId(offerId);
        for(Reservation q: reservations){
            if((q.getStartDate().compareTo(startDateReservation) <= 0) && (startDateReservation.compareTo(q.getEndDate()) <= 0))
                return false;
            if((q.getStartDate().compareTo(endDateReservation) <= 0) && (endDateReservation.compareTo(q.getEndDate()) <= 0))
                return false;
        }
        return true;
    }
    @Override
    public Boolean isAvailableClient(String emailClient, String startReservation, String endReservation){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDateReservation = LocalDate.parse(startReservation, formatter);
        LocalDate endDateReservation = LocalDate.parse(endReservation, formatter);
        List<Reservation> reservations = reservationRepository.findByClientEmail(emailClient);
        for(Reservation q: reservations){
            if((q.getStartDate().compareTo(startDateReservation) <= 0) && (startDateReservation.compareTo(q.getEndDate()) <= 0))
                return false;
            if((q.getStartDate().compareTo(endDateReservation) <= 0) && (endDateReservation.compareTo(q.getEndDate()) <= 0))
                return false;
        }
        return true;
    }
    @Override
    @Transactional
    public Integer makeReservationOwner(NewReservationDTO dto){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDateReservation = LocalDate.parse(dto.getStartDateReservation(), formatter);
        LocalDate endDateReservation = startDateReservation.plusDays(dto.getDaysReservation());
        Offer offer = offerRepository.findOfferById(dto.getOfferId());
        Client client = clientRepository.findByEmail(dto.getClientUserName());

        List<AdditionalService> newAdditionalService = new ArrayList<>();
        for(AdditionalService a: dto.getServices()){
            AdditionalService additionalService = additionalServiceRepository.save(new AdditionalService(a.getName(),a.getPrice()));
            newAdditionalService.add(additionalService);
        }

        Reservation reservation = new Reservation(startDateReservation, endDateReservation,newAdditionalService, dto.getPrice()*dto.getDaysReservation(), dto.getPeopleNum(), offer, client, false);
        Reservation newReservation = reservationRepository.save(reservation);
        offer.getReservations().add(newReservation);
        offerRepository.save(offer);
        sendEmail(client.getEmail(), reservation);
        return newReservation.getId();
    }

    @Transactional
    void sendEmail(String client, Reservation reservation){
        emailSender.notifyClientNewReservation("markoooperic123+++fdf@gmail.com", reservation);

    }
//    @Override
//    public void addAdditionalServices(List<AdditionalServiceDTO> additionalServiceDTOs, Integer reservationId) {
//
//        List<Optional<AdditionalService>> services = new ArrayList<>();
//        for(AdditionalServiceDTO s : additionalServiceDTOs){
//            services.add(additionalServiceRepository.findById(s.getId()));
//        }
//        List<AdditionalService> additionalServices = new ArrayList<>();
//        for (Optional<AdditionalService> x : services) {
//            x.ifPresent(additionalServices::add);
//        }
//        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
//
//        if(reservation.isPresent()){
//            Reservation r = reservation.get();
//            r.setAdditionalServices(additionalServices);
//            reservationRepository.save(r);
//        }
//
//    }

    @Override
    @Transactional
    public List<ClientDTO> getClientByCottageOwnerEmail(String email){
        LocalDate today = LocalDate.now();
        List<Reservation> currentReservation = reservationRepository.findCurrentByOwnerEmail(email, today);
        List<ClientDTO> clients = new ArrayList<>();
        for(Reservation r: currentReservation){
            ClientCategory category = clientCategoryRepository.findByMatchingInterval(r.getClient().getPoints()).get(0);
            ClientDTO dto = new ClientDTO(r.getClient(), r.getOffer().getId(), category.getName());
            clients.add(dto);
        }
        return clients;
    }
    @Override
    @Transactional
    public List<ClientDTO> getClientByShipOwnerEmail(String email){
        LocalDate today = LocalDate.now();
        List<Reservation> currentReservation = reservationRepository.findCurrentByShipOwnerEmail(email, today);
        List<ClientDTO> clients = new ArrayList<>();
        for(Reservation r: currentReservation){
            ClientCategory category = clientCategoryRepository.findByMatchingInterval(r.getClient().getPoints()).get(0);
            ClientDTO dto = new ClientDTO(r.getClient(), r.getOffer().getId(), category.getName());
            clients.add(dto);
        }
        return clients;
    }
    @Override
    @Transactional
    public List<ClientDTO> getClientByInstructorEmail(String email){
        LocalDate today = LocalDate.now();
        List<Reservation> currentReservation = reservationRepository.findCurrentByInstructorEmail(email, today);
        List<ClientDTO> clients = new ArrayList<>();
        for(Reservation r: currentReservation){
            ClientCategory category = clientCategoryRepository.findByMatchingInterval(r.getClient().getPoints()).get(0);
            ClientDTO dto = new ClientDTO(r.getClient(), r.getOffer().getId(), category.getName());
            clients.add(dto);
        }
        return clients;
    }


    @Override
    @Transactional
    public List<ReservationDTO> getAllReservation(String ownerId, String role) throws IOException {
        List<Reservation> reservations = new ArrayList<>();
        if(role.equals(UserType.SHIP_OWNER.toString()))
            reservations = reservationRepository.findPastReservationByShipOwnerEmail(ownerId, LocalDate.now());
        else if(role.equals(UserType.COTTAGE_OWNER.toString()))
            reservations = reservationRepository.findPastReservationByCottageOwnerEmail(ownerId, LocalDate.now());
        return getReservationDTOS(reservations);
    }

    private List<ReservationDTO> getReservationDTOS(List<Reservation> reservations) throws IOException {
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation r: reservations){
            ReservationDTO dto = new ReservationDTO(r);
//            dto.setAdditionalServices(additionalServiceService.getAdditionalServices(r.getOffer()));
            dto.setAdditionalServices(makeAdditionalServicesDTO(r.getAdditionalServices()));
            reservationDTOS.add(dto);
        }
        return reservationDTOS;
    }

    private List<AdditionalServiceDTO> makeAdditionalServicesDTO(List<AdditionalService> services){
        List<AdditionalServiceDTO> retList = new ArrayList<>();
        for(AdditionalService as : services){
            retList.add(new AdditionalServiceDTO(as));
        }
        return retList;
    }

    @Override
    @Transactional
    public List<ReservationDTO> getUpcomingCottageReservationsByClient(String email) throws IOException {
        List<Reservation> reservations = reservationRepository.getUpcomingCottageReservationsByClient(email, LocalDate.now());
        return getReservationDTOS(reservations);
    }

    @Override
    @Transactional
    public List<ReservationDTO> getUpcomingShipReservationsByClient(String email) throws IOException {
        List<Reservation> reservations = reservationRepository.getUpcomingShipReservationsByClient(email, LocalDate.now());
        return getReservationDTOS(reservations);
    }

    @Override
    @Transactional
    public List<ReservationDTO> getUpcomingAdventureReservationsByClient(String email) throws IOException {
        List<Reservation> reservations = reservationRepository.getUpcomingAdventureReservationsByClient(email, LocalDate.now());
        return getReservationDTOS(reservations);
    }

    @Override
    public void cancelReservation(Integer id) throws CancellingReservationException {
        Optional<Reservation> r = reservationRepository.findById(id);
        LocalDate today = r.get().getStartDate();
        LocalDate boundary = today.minusDays(3);
        Optional<Integer> exists = Optional.ofNullable(reservationRepository.checkCancelCondition(id, boundary, LocalDate.now()));
        if(exists.isPresent())
            throw new CancellingReservationException("Cannot cancel reservation.");
        reservationRepository.deleteById(id);
    }

//    private String localDateToString(LocalDate date){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
//        return formatter.format(date);
//    }
//
//    private String getOfferPhoto(Offer offer) throws IOException {
//        String photoName = "no-image.png";
//        if(!offer.getPhotos().isEmpty()) {
//            photoName = offer.getPhotos().get(0).getPath();
//        }
//        return convertPhoto(photoName);
//    }
//
//    private String convertPhoto(String photoName) throws IOException {
//        String pathFile = "./src/main/frontend/src/components/images/" + photoName;
//        byte[] bytes = Files.readAllBytes(Paths.get(pathFile));
//        String photoData = Base64.getEncoder().encodeToString(bytes);
//        return photoData;
//    }
}
