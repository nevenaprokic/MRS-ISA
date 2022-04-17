package com.booking.ISAbackend.service.impl;

import java.util.List;
import java.util.Optional;

import com.booking.ISAbackend.model.CottageOwner;
import com.booking.ISAbackend.model.Instructor;
import com.booking.ISAbackend.model.Owner;
import com.booking.ISAbackend.repository.CottageOwnerRepository;
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
	@Autowired
	private CottageOwnerRepository cottageOwnerRepository;

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
	public Instructor findInstructorByEmail(String email) {
		MyUser user = userRepository.findByEmail(email);
		Optional<Instructor> instructor = instructorRepository.findById(user.getId());
		return instructor.orElse(null);
	}
	@Override
	public CottageOwner findCottageOwnerByEmail(String email){
		MyUser user = userRepository.findByEmail(email);
		Optional<CottageOwner> cottageOwner = cottageOwnerRepository.findById(user.getId());
		return cottageOwner.orElse(null);
	}


}
