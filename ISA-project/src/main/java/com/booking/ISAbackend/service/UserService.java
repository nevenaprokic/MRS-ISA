package com.booking.ISAbackend.service;

import java.util.List;

import com.booking.ISAbackend.model.MyUser;

import com.booking.ISAbackend.dto.UserRequest;

public interface UserService {
	
	MyUser findById(Integer id);
	MyUser findByEmail(String email);
    List<MyUser> findAll ();
    MyUser save(UserRequest userRequest);
}
