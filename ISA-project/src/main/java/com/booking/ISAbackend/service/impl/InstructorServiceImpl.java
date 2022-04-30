package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.InstructorProfileData;
import com.booking.ISAbackend.model.Instructor;
import com.booking.ISAbackend.repository.InstructorRepository;
import com.booking.ISAbackend.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public List<InstructorProfileData> searchInstructors(String firstName, String lastName, String address, String phoneNumber) {
        List<InstructorProfileData> retList = new ArrayList<>();
        List<Instructor> instructors = instructorRepository.searchInstructors(firstName, lastName, address, phoneNumber);
        for(Instructor i : instructors){
            retList.add(new InstructorProfileData(i));
        }
        return retList;
    }

    @Override
    public List<InstructorProfileData> findAll() {
        List<InstructorProfileData> retList = new ArrayList<>();
        List<Instructor> instructors = instructorRepository.findAll();
        for(Instructor i : instructors){
            retList.add(new InstructorProfileData(i));
        }
        return retList;
    }
}
