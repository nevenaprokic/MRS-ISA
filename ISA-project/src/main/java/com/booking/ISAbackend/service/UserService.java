package com.booking.ISAbackend.service;

import java.util.HashMap;
import java.util.List;

import com.booking.ISAbackend.dto.InstructorNewDataDTO;
import com.booking.ISAbackend.dto.NewOwnerDataDTO;
import com.booking.ISAbackend.dto.UserProfileData;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPasswordException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;

import com.booking.ISAbackend.model.*;

import com.booking.ISAbackend.dto.UserRequest;

public interface UserService {
	
	MyUser findById(Integer id);
	MyUser findByEmail(String email);
    List<MyUser> findAll ();
    MyUser save(UserRequest userRequest);
    Instructor findInstructorByEmail(String email);
    void changeOwnerData(NewOwnerDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException;
    void changeInstrctorData(InstructorNewDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException;


    CottageOwner findCottageOwnerByEmail(String email);

    Boolean isOldPasswordCorrect(String email, HashMap<String, String> data) throws InvalidPasswordException;
    ShipOwner findShipOwnerByEmail(String email);


    UserProfileData findAdminByEmail(String email);
    void changeAdminData(UserProfileData newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException;

}
