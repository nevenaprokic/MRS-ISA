package com.booking.ISAbackend.service;

import java.util.HashMap;
import java.util.List;

import com.booking.ISAbackend.dto.*;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPasswordException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;

import com.booking.ISAbackend.model.*;

public interface UserService {
	
	MyUser findById(Integer id);
	MyUser findByEmail(String email);
    List<MyUser> findAll ();
    MyUser save(UserRequest userRequest);
    InstructorProfileData getInstructorDataByEmail(String email);
    void changeOwnerData(NewOwnerDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException;
    void changeInstrctorData(InstructorNewDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException;


    CottageOwner findCottageOwnerByEmail(String email);

    Boolean isOldPasswordCorrect(String email, HashMap<String, String> data) throws InvalidPasswordException;
    ShipOwner findShipOwnerByEmail(String email);


    UserProfileData findAdminByEmail(String email);
    void changeAdminData(UserProfileData newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException;

    Instructor findInstructorByEmail(String email);
    void changeCottageOwnerData(CottageOwnerNewDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException;
    void changeShipOwnerData(ShipOwnerNewDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException;
    boolean sendDeleteRequestCottageOwner(String email, String reason);
}
