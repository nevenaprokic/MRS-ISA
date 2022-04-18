package com.booking.ISAbackend.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


import com.booking.ISAbackend.dto.NewOwnerDataDTO;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPasswordException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;
import com.booking.ISAbackend.model.Address;

import com.booking.ISAbackend.model.CottageOwner;
import com.booking.ISAbackend.model.Instructor;
import com.booking.ISAbackend.repository.CottageOwnerRepository;
import com.booking.ISAbackend.repository.InstructorRepository;
import com.booking.ISAbackend.repository.OwnerRepository;
import com.booking.ISAbackend.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.booking.ISAbackend.model.MyUser;

import com.booking.ISAbackend.dto.UserRequest;
import com.booking.ISAbackend.repository.UserRepository;
import com.booking.ISAbackend.service.UserService;
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	private PasswordEncoder passwordEncoder;

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

	@Override
	public Boolean isOldPasswordCorrect(String email, HashMap<String, String> data) throws InvalidPasswordException {
		MyUser currentUser = userRepository.findByEmail(email);
		String newPasswordHash = passwordEncoder.encode(data.get("newPassword1"));
		if (data.get("newPassword1").equals(data.get("newPassword2")) && passwordEncoder.matches(data.get("oldPassword"), currentUser.getPassword())) {
			currentUser.setPassword(newPasswordHash);
			userRepository.save(currentUser);
			return true;
		}
		throw new InvalidPasswordException("Data is invalid.");
	}

	@Override
	@Transactional
	public void changeOwnerData(NewOwnerDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException {
		Instructor instructor = findInstructorByEmail(newData.getEmail());
		Address address = instructor.getAddress();
		if(instructor != null){
			if(!newData.getFirstName().equals("")){
				if(Validator.onlyLetterAndSpacesValidation(newData.getFirstName())){
					instructor.setFirstName(newData.getFirstName());
				}


			}
			if(!newData.getLastName().equals("")){
				if(Validator.onlyLetterAndSpacesValidation(newData.getLastName())){
					instructor.setLastName(newData.getLastName());
				}

			}
			if(!newData.getPhoneNumber().equals("")){
				if(Validator.phoneNumberValidation(newData.getPhoneNumber())){
					instructor.setPhoneNumber(newData.getPhoneNumber());
				}


			}
			if(!newData.getStreet().equals("")){
				if(Validator.isValidAdress(newData.getStreet(), address.getCity(), address.getState()))
				{
					address.setStreet(newData.getStreet());
				}

			}
			if(!newData.getCity().equals("")){
				if(Validator.isValidAdress(address.getStreet(), newData.getCity(), address.getState()))
				{
					address.setCity(newData.getCity());
				}


			}
			if(!newData.getState().equals("")){
				if(Validator.isValidAdress(address.getStreet(), address.getCity(), newData.getState())) {
					address.setState(newData.getState());
				}

			}
			if(!newData.getBiography().equals("")){
				instructor.setBiography(newData.getBiography());

			}
		instructorRepository.save(instructor);
		}

	}

	private boolean newOwnerDatValidation(NewOwnerDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException {
		boolean validation = Validator.phoneNumberValidation(newData.getPhoneNumber()) &&
				Validator.onlyLetterAndSpacesValidation(newData.getFirstName()) &&
				Validator.onlyLetterAndSpacesValidation(newData.getLastName()) &&
				Validator.isValidAdress(newData.getStreet(), newData.getCity(), newData.getStreet());
		return validation;

	}


}
