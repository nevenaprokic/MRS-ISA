package com.booking.ISAbackend.service;

import java.util.List;
import java.util.Optional;

import com.booking.ISAbackend.dto.NewOwnerDataDTO;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;
import com.booking.ISAbackend.model.Instructor;
import com.booking.ISAbackend.model.MyUser;

import com.booking.ISAbackend.dto.UserRequest;

public interface UserService {
	
	MyUser findById(Integer id);
	MyUser findByEmail(String email);
    List<MyUser> findAll ();
    MyUser save(UserRequest userRequest);
    Instructor findInstructorByEmail(String email);
    void changeOwnerData(NewOwnerDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException;
}
