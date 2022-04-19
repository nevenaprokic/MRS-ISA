package com.booking.ISAbackend.service;

import java.util.HashMap;
import java.util.List;

import com.booking.ISAbackend.dto.NewOwnerDataDTO;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPasswordException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;

import com.booking.ISAbackend.model.CottageOwner;
import com.booking.ISAbackend.model.Instructor;
import com.booking.ISAbackend.model.MyUser;

import com.booking.ISAbackend.dto.UserRequest;
import com.booking.ISAbackend.model.ShipOwner;

public interface UserService {
	
	MyUser findById(Integer id);
	MyUser findByEmail(String email);
    List<MyUser> findAll ();
    MyUser save(UserRequest userRequest);
    Instructor findInstructorByEmail(String email);
    void changeOwnerData(NewOwnerDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException;
    CottageOwner findCottageOwnerByEmail(String email);

    Boolean isOldPasswordCorrect(String email, HashMap<String, String> data) throws InvalidPasswordException;
    ShipOwner findShipOwnerByEmail(String email);
}
