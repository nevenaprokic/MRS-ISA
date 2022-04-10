package com.booking.ISAbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.ISAbackend.model.MyUser;

import com.booking.ISAbackend.dto.UserRequest;
import com.booking.ISAbackend.repository.UserRepository;
import com.booking.ISAbackend.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public MyUser findById(Integer id) {
		return userRepository.findById(id).orElseGet(null);
	}

	@Override
	public MyUser findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<MyUser> findAll() {
		return userRepository.findAll();
	}

	@Override
	public MyUser save(UserRequest userRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
