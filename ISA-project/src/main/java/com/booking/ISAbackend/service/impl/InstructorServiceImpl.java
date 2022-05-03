package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.AdventureDTO;
import com.booking.ISAbackend.dto.InstructorProfileData;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.model.Instructor;
import com.booking.ISAbackend.model.MyUser;
import com.booking.ISAbackend.repository.InstructorRepository;
import com.booking.ISAbackend.service.AdventureService;
import com.booking.ISAbackend.service.InstructorService;
import com.booking.ISAbackend.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private AdventureService adventureService;

    @Override
    public List<InstructorProfileData> searchInstructors(String firstName, String lastName, String address, String phoneNumber) throws InvalidPhoneNumberException {
        List<InstructorProfileData> retList = new ArrayList<>();
        if(phoneNumber.equals("") || Validator.phoneNumberValidation(phoneNumber)){
            List<Instructor> instructors = instructorRepository.searchInstructors(firstName, lastName, address, phoneNumber);
            makeInstructorDTOs(retList, instructors);
            return retList;
        }
        return retList;
    }

    @Override
    public List<InstructorProfileData> findAll() {
        List<InstructorProfileData> retList = new ArrayList<>();
        List<Instructor> instructors = instructorRepository.findAll();
        makeInstructorDTOs(retList, instructors);
        return retList;
    }

    private void makeInstructorDTOs(List<InstructorProfileData> retList, List<Instructor> instructors) {
        for (Instructor i : instructors) {
            List<AdventureDTO> adventures = adventureService.getInstructorAdventures(i.getEmail());
            InstructorProfileData dto = new InstructorProfileData(i);
            dto.setAdventures(adventures);
            retList.add(dto);
        }
    }



}
