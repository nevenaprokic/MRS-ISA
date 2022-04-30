package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.InstructorProfileData;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.model.Instructor;

import java.util.List;

public interface InstructorService {
    List<InstructorProfileData> searchInstructors(String firstName, String lastName, String address, String phoneNumber) throws InvalidPhoneNumberException;

    List<InstructorProfileData> findAll();
}
