package com.booking.ISAbackend.student1;


import com.booking.ISAbackend.config.WebConfig;
import com.booking.ISAbackend.dto.ReservationParamsDTO;
import com.booking.ISAbackend.exceptions.ClientNotAvailableException;
import com.booking.ISAbackend.exceptions.NotAllowedToMakeReservationException;
import com.booking.ISAbackend.exceptions.OfferNotAvailableException;
import com.booking.ISAbackend.exceptions.PreviouslyCanceledReservationException;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.*;
import com.booking.ISAbackend.service.impl.ClientServiceImpl;
import com.booking.ISAbackend.service.impl.ReservationServiceImpl;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class UnitTests {

    @InjectMocks
    ClientServiceImpl clientServiceMock;

    @InjectMocks
    ReservationServiceImpl reservationServiceMock;

    @Mock
    ClientRepository clientRepository;

    @Mock
    AdditionalServiceRepository additionalServiceRepository;

    @Mock
    OfferRepository offerRepository;

    @Mock
    QuickReservationRepository quickReservationRepository;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    MarkRepository markRepository;

    @Test()
    public void makeReview() throws Exception {
        // 1. Definisanje ponašanja mock objekata
        Reservation r = new Reservation();
        r.setId(1);
        r.setStartDate(LocalDate.of(2023, 4, 17));
        r.setStartDate(LocalDate.of(2023, 4, 18));
        r.setPrice(600.00);
        r.setDeleted(false);
        r.setNumberOfPerson(4);

        Client c = new Client();
        c.setPoints(0);
        c.setPenal(2);
        c.setId(1);
        c.setFirstName("Petar");
        c.setLastName("Peric");
        c.setPhoneNumber("062-111-111");
        c.setEmail("pera@gmail.com");
        c.setDeleted(false);
        c.setEmailVerified(true);
        
        Mark m = new Mark();
        m.setClient(c);
        m.setReservation(r);
        m.setComment("Super je bilo!");

        Mockito.when(reservationRepository.findById(1)).thenReturn(java.util.Optional.of(r));
        Mockito.when(clientRepository.findByEmail("pera@gmail.com")).thenReturn(c);
        Optional<Mark> opt = Optional.empty();
        Mockito.when(markRepository.alreadyReviewed(1, 1)).thenReturn(opt);
        Mockito.when(markRepository.save(m)).thenReturn(m);

        // 2. Akcija
        //boolean canReserve = clientService.canReserve("pera@gmail.com");
        clientServiceMock.makeReview(5, 1, "Super je bilo!", "pera@gmail.com");

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        //Assertions.assertThrows(FeedbackAlreadyGivenException.class, () -> clientService.makeReview(5, 1, "Super je bilo!", "pera@gmail.com"));
        //Mockito.verify(markRepository, Mockito.times(1)).save(m);

        Mockito.verify(markRepository, Mockito.times(1)).alreadyReviewed(1, 1);
    }

    @Test
    public void makeReservation() throws NotAllowedToMakeReservationException, OfferNotAvailableException, PreviouslyCanceledReservationException, ClientNotAvailableException, InterruptedException {

        Reservation r = new Reservation();
        r.setId(13);

        ReservationParamsDTO params = new ReservationParamsDTO();
        params.setEmail("pera@gmail.com");
        params.setDate(LocalDate.of(2023, 6, 30));
        params.setEndingDate(LocalDate.of(2023, 7, 1));
        params.setGuests(1);
        params.setServices(new ArrayList<AdditionalService>());
        params.setTotal(40.00);
        params.setOfferId(2);

        Client c = new Client();
        c.setPoints(0);
        c.setPenal(2);
        c.setId(1);
        c.setFirstName("Petar");
        c.setLastName("Peric");
        c.setPhoneNumber("062-111-111");
        c.setEmail("pera@gmail.com");
        c.setDeleted(false);
        c.setEmailVerified(true);

        Offer o = new Offer();
        o.setVersion(1L);
        o.setUnavailableDate(new ArrayList<>());
        List<Offer> nonAvailables = new ArrayList<>();
        Offer nonAvailable = new Offer();
        nonAvailable.setId(3);
        nonAvailables.add(nonAvailable);

        Optional<Integer> opt = Optional.empty();
        Mockito.when(reservationRepository.checkIfCanceled(params.getEmail(), params.getDate(), params.getOfferId())).thenReturn(opt);
        Mockito.when(clientRepository.getPenalties(params.getEmail())).thenReturn(2);
        Mockito.when(clientRepository.findByEmail(params.getEmail())).thenReturn(c);
        Mockito.when(offerRepository.findOfferById(params.getOfferId())).thenReturn(o);

        reservationServiceMock.makeReservation(params);

        Mockito.when(offerRepository.nonAvailableOffers(params.getDate(), params.getEndingDate())).thenReturn(nonAvailables);

        Mockito.verify(reservationRepository, Mockito.times(0)).save(Mockito.any(Reservation.class));
    }

    @Test(expected = NotAllowedToMakeReservationException.class)
    public void makeReservationFail() throws NotAllowedToMakeReservationException, OfferNotAvailableException, PreviouslyCanceledReservationException, ClientNotAvailableException, InterruptedException {

        Reservation r = new Reservation();
        r.setId(13);

        ReservationParamsDTO params = new ReservationParamsDTO();
        params.setEmail("pera@gmail.com");
        params.setDate(LocalDate.of(2023, 6, 30));
        params.setEndingDate(LocalDate.of(2023, 7, 1));
        params.setGuests(1);
        params.setServices(new ArrayList<AdditionalService>());
        params.setTotal(40.00);
        params.setOfferId(2);

        Client c = new Client();
        c.setPoints(0);
        c.setPenal(2);
        c.setId(1);
        c.setFirstName("Petar");
        c.setLastName("Peric");
        c.setPhoneNumber("062-111-111");
        c.setEmail("pera@gmail.com");
        c.setDeleted(false);
        c.setEmailVerified(true);

        Offer o = new Offer();
        o.setUnavailableDate(new ArrayList<>());
        o.setVersion(1L);
        List<Offer> nonAvailables = new ArrayList<>();
        Offer nonAvailable = new Offer();
        nonAvailable.setId(3);
        nonAvailables.add(nonAvailable);

        Optional<Integer> opt = Optional.empty();
        Mockito.when(reservationRepository.checkIfCanceled(params.getEmail(), params.getDate(), params.getOfferId())).thenReturn(opt);
        Mockito.when(clientRepository.getPenalties(params.getEmail())).thenReturn(3);
        Mockito.when(clientRepository.findByEmail(params.getEmail())).thenReturn(c);
        Mockito.when(offerRepository.findOfferById(params.getOfferId())).thenReturn(o);

        reservationServiceMock.makeReservation(params);

        Assertions.assertThrows(NotAllowedToMakeReservationException.class, () -> reservationServiceMock.makeReservation(params));
        Mockito.verifyNoInteractions(additionalServiceRepository);
    }

//    //@Test//(expected = ObjectOptimisticLockingFailureException.class)
//    public void testOptimisticLockingScenario() throws Throwable {
//
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        Future<?> future1 = executor.submit(new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println("Startovan Thread 1");
//                Reservation r = new Reservation();
//                r.setId(13);
//
//                ReservationParamsDTO params = new ReservationParamsDTO();
//                params.setEmail("pera@gmail.com");
//                params.setDate(LocalDate.of(2023, 6, 30));
//                params.setEndingDate(LocalDate.of(2023, 7, 1));
//                params.setGuests(1);
//                params.setServices(new ArrayList<AdditionalService>());
//                params.setTotal(40.00);
//                params.setOfferId(2);
//
//                Client c = new Client();
//                c.setPoints(0);
//                c.setPenal(2);
//                c.setId(1);
//                c.setFirstName("Petar");
//                c.setLastName("Peric");
//                c.setPhoneNumber("062-111-111");
//                c.setEmail("pera@gmail.com");
//                c.setDeleted(false);
//                c.setEmailVerified(true);
//
//                Offer o = new Offer();
//                o.setVersion(1L);
//                o.setUnavailableDate(new ArrayList<>());
//                List<Offer> nonAvailables = new ArrayList<>();
//                Offer nonAvailable = new Offer();
//                nonAvailable.setId(3);
//                nonAvailables.add(nonAvailable);
//
//                Optional<Integer> opt = Optional.empty();
//                Mockito.when(reservationRepository.checkIfCanceled(params.getEmail(), params.getDate(), params.getOfferId())).thenReturn(opt);
//                Mockito.when(clientRepository.getPenalties(params.getEmail())).thenReturn(2);
//                Mockito.when(clientRepository.findByEmail(params.getEmail())).thenReturn(c);
//                Mockito.when(offerRepository.findOfferById(params.getOfferId())).thenReturn(o);
//
//                Reservation res = null;
//                try {
//                    res = reservationServiceMock.makeReservation(params);
//                } catch (OfferNotAvailableException e) {
//                    e.printStackTrace();
//                } catch (PreviouslyCanceledReservationException e) {
//                    e.printStackTrace();
//                } catch (ClientNotAvailableException e) {
//                    e.printStackTrace();
//                } catch (NotAllowedToMakeReservationException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
//                System.out.println("KRecem da cuvam u thread 1");
//                reservationServiceMock.saveReservation(res);
//            }
//        });
//        executor.submit(new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println("Startovan Thread 2");
//                Reservation r = new Reservation();
//                r.setId(13);
//
//                ReservationParamsDTO params = new ReservationParamsDTO();
//                params.setEmail("pera@gmail.com");
//                params.setDate(LocalDate.of(2022, 6, 30));
//                params.setEndingDate(LocalDate.of(2022, 7, 1));
//                params.setGuests(1);
//                params.setServices(new ArrayList<AdditionalService>());
//                params.setTotal(40.00);
//                params.setOfferId(2);
//
//                Client c = new Client();
//                c.setPoints(0);
//                c.setPenal(2);
//                c.setId(1);
//                c.setFirstName("Petar");
//                c.setLastName("Peric");
//                c.setPhoneNumber("062-111-111");
//                c.setEmail("pera@gmail.com");
//                c.setDeleted(false);
//                c.setEmailVerified(true);
//
//                Offer o = new Offer();
//                o.setVersion(2L);
//                o.setUnavailableDate(new ArrayList<>());
//                List<Offer> nonAvailables = new ArrayList<>();
//                Offer nonAvailable = new Offer();
//                nonAvailable.setId(3);
//                nonAvailables.add(nonAvailable);
//
//                Optional<Integer> opt = Optional.empty();
//                Mockito.when(reservationRepository.checkIfCanceled(params.getEmail(), params.getDate(), params.getOfferId())).thenReturn(opt);
//                Mockito.when(clientRepository.getPenalties(params.getEmail())).thenReturn(2);
//                Mockito.when(clientRepository.findByEmail(params.getEmail())).thenReturn(c);
//                Mockito.when(offerRepository.findOfferById(params.getOfferId())).thenReturn(o);
//
//                Reservation res = null;
//                try {
//                    res = reservationServiceMock.makeReservation(params);
//                    System.out.println("Napravio rez u thread 2");
//                } catch (OfferNotAvailableException e) {
//                    e.printStackTrace();
//                } catch (PreviouslyCanceledReservationException e) {
//                    e.printStackTrace();
//                } catch (ClientNotAvailableException e) {
//                    e.printStackTrace();
//                } catch (NotAllowedToMakeReservationException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                reservationServiceMock.saveReservation(res);
//                System.out.println("Sacuvao rez u thread 2");
//            }
//        });
//        try {
//            future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
//        } catch (ExecutionException e) {
//            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
//            throw e.getCause();
//        } catch (InterruptedException e) {
//            System.out.println("Nije dobro "); // u pitanju je bas ObjectOptimisticLockingFailureException
//            e.printStackTrace();
//        }
//        executor.shutdown();
//
//    }


}
