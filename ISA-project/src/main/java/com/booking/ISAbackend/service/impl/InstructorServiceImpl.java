package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.AdventureDTO;
import com.booking.ISAbackend.dto.InstructorProfileData;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.model.DeleteRequest;
import com.booking.ISAbackend.model.Instructor;
import com.booking.ISAbackend.model.MyUser;
import com.booking.ISAbackend.model.Reservation;
import com.booking.ISAbackend.repository.DeleteRequestRepository;
import com.booking.ISAbackend.repository.InstructorRepository;
import com.booking.ISAbackend.repository.ReservationRepository;
import com.booking.ISAbackend.repository.UserRepository;
import com.booking.ISAbackend.service.AdventureService;
import com.booking.ISAbackend.service.InstructorService;
import com.booking.ISAbackend.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private DeleteRequestRepository deleteRequestRepository;

    @Override
    @Transactional
    public List<InstructorProfileData> searchInstructors(String firstName, String lastName, String address, String phoneNumber) throws InvalidPhoneNumberException, IOException {
        List<InstructorProfileData> retList = new ArrayList<>();
        if(phoneNumber.equals("") || Validator.phoneNumberValidation(phoneNumber)){
            List<Instructor> instructors = instructorRepository.searchInstructors(firstName, lastName, address, phoneNumber);
            makeInstructorDTOs(retList, instructors);
            return retList;
        }
        return retList;
    }

    @Override
    @Transactional
    public List<InstructorProfileData> findAll() throws IOException {
        List<InstructorProfileData> retList = new ArrayList<>();
        List<Instructor> instructors = instructorRepository.findAll();
        makeInstructorDTOs(retList, instructors);
        return retList;
    }

    @Override
    public boolean sendDeleteRequest(String email, String reason) {
        MyUser user = userRepository.findByEmail(email);
        List<Reservation> listOfReservation = reservationRepository.findByCottageOwnerEmail(email);
        LocalDate today = LocalDate.now();
        for(Reservation r:listOfReservation){
            if((today.compareTo(r.getEndDate())<0)){
                return false;
            }
        }
        DeleteRequest deleteRequest = new DeleteRequest(reason, user);
        deleteRequestRepository.save(deleteRequest);
        return true;
    }

    private void makeInstructorDTOs(List<InstructorProfileData> retList, List<Instructor> instructors) throws IOException {
        for (Instructor i : instructors) {
            List<AdventureDTO> adventures = adventureService.getInstructorAdventures(i.getEmail());
            InstructorProfileData dto = new InstructorProfileData(i);
            dto.setAdventures(adventures);
            retList.add(dto);
        }
    }

}
