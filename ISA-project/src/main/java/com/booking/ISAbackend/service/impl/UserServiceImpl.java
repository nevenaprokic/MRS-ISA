package com.booking.ISAbackend.service.impl;

import java.util.List;
import java.util.Optional;

import com.booking.ISAbackend.model.Instructor;
import com.booking.ISAbackend.model.Owner;
import com.booking.ISAbackend.repository.InstructorRepository;
import com.booking.ISAbackend.repository.OwnerRepository;
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
	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private OwnerRepository ownerRepository;

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

	@Override
	public MyUser findInstructorByEmail(String email) {
//		MyUser user = userRepository.findByEmail(email);
//		System.out.println(user.getId());
//		Optional<Instructor> instructor = instructorRepository.findById(user.getId());
//		Optional<Owner> owner = ownerRepository.findById(user.getId());
//		System.out.println(instructor);

		return userRepository.findByEmail(email);
	}


}
