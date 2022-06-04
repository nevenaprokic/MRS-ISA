package com.booking.ISAbackend.student1;


import com.booking.ISAbackend.config.WebConfig;
import com.booking.ISAbackend.model.Client;
import com.booking.ISAbackend.model.Mark;
import com.booking.ISAbackend.model.Reservation;
import com.booking.ISAbackend.repository.ClientRepository;
import com.booking.ISAbackend.repository.MarkRepository;
import com.booking.ISAbackend.repository.ReservationRepository;
import com.booking.ISAbackend.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class ClientServiceTest {

    @InjectMocks
    ClientServiceImpl clientService;

    @Mock
    ClientRepository clientRepository;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    MarkRepository markRepository;

    @Test()
    public void makeReview() {
        // 1. Definisanje ponašanja mock objekata
        Reservation r = new Reservation();
        r.setId(1);
        r.setStartDate(LocalDate.of(2022, 4, 17));
        r.setStartDate(LocalDate.of(2022, 4, 18));
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

        Mockito.when(reservationRepository.findById(1)).thenReturn(java.util.Optional.of(r));
        Mockito.when(clientRepository.findByEmail("pera@gmail.com")).thenReturn(c);
        Mockito.when(markRepository.alreadyReviewed(1, 1)).thenReturn(null);

        markRepository.save(m);

        // 2. Akcija
        boolean canReserve = clientService.canReserve("pera@gmail.com");

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        Mockito.verify(markRepository, Mockito.times(1)).save(m);
    }
}
