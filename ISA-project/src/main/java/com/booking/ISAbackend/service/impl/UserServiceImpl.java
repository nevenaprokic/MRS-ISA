package com.booking.ISAbackend.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


import com.booking.ISAbackend.dto.*;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPasswordException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;
import com.booking.ISAbackend.model.*;

import com.booking.ISAbackend.repository.*;
import com.booking.ISAbackend.service.AdventureService;
import com.booking.ISAbackend.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	private ShipOwnerRepository shipOwnerRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private DeleteRequestRepository deleteRequestRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private OwnerCategoryServiceImpl ownerCategoryService;

	@Autowired
	private AddressRepository addressRepository;

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
	@Transactional
	public InstructorProfileData getInstructorDataByEmail(String email) {
		InstructorProfileData dto = null;
		Instructor i = findInstructorByEmail(email);
		if(i != null){
			OwnerCategory category = ownerCategoryService.findByReservationpoints(i.getPoints()).get(0);
			dto = new InstructorProfileData(i, category);
			return dto;
		}
		return dto;
	}

	@Override
	@Transactional
	public CottageOwnerProfileInfoDTO getCottageOwnerDataByEmail(String email) {
		CottageOwnerProfileInfoDTO dto = null;
		CottageOwner cottageOwner = findCottageOwnerByEmail(email);
		if(cottageOwner != null){
			OwnerCategory category = ownerCategoryService.findByReservationpoints(cottageOwner.getPoints()).get(0);
			dto = new CottageOwnerProfileInfoDTO(cottageOwner, category);
			return dto;
		}
		return dto;
	}

	@Override
	public CottageOwner findCottageOwnerByEmail(String email){
		MyUser user = userRepository.findByEmail(email);
		Optional<CottageOwner> cottageOwner = cottageOwnerRepository.findById(user.getId());

		return cottageOwner.orElse(null);
	}

	@Override
	@Transactional
	public ShipOwnerProfileInfoDTO getShipOwnerDataByEmail(String email) {
		ShipOwnerProfileInfoDTO dto = null;
		ShipOwner shipOwner = findShipOwnerByEmail(email);
		if(shipOwner != null){
			OwnerCategory category = ownerCategoryService.findByReservationpoints(shipOwner.getPoints()).get(0);
			dto = new ShipOwnerProfileInfoDTO(shipOwner, category);
			return dto;
		}
		return dto;
	}
	@Override
	public ShipOwner findShipOwnerByEmail(String email){
		MyUser user = userRepository.findByEmail(email);
		Optional<ShipOwner> shipOwner = shipOwnerRepository.findById(user.getId());
		return shipOwner.orElse(null);
	}

	@Override
	@Transactional
	public UserProfileData findAdminByEmail(String email) {
		MyUser user = userRepository.findByEmail(email);
		UserProfileData adminData = new UserProfileData(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(),
							user.getAddress().getStreet(), user.getAddress().getCity(), user.getAddress().getState());
		return adminData;
	}

	@Override
	@Transactional
	public void changeAdminData(UserProfileData newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException {
		boolean validation = validateUserNewData(newData);
		if (validation){
			Admin admin = (Admin) userRepository.findByEmail(newData.getEmail());
			admin.setFirstName(newData.getFirstName());
			admin.setLastName(newData.getLastName());
			admin.setPhoneNumber(newData.getPhoneNumber());
			Address newAddress = new Address(newData.getStreet(), newData.getCity(), newData.getState());
			addressRepository.save(newAddress);
			admin.setAddress(newAddress);
			userRepository.save(admin);
		}

	}

	private boolean validateUserNewData(UserProfileData newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException {
		return  Validator.onlyLetterAndSpacesValidation(newData.getFirstName()) &&
				Validator.onlyLetterAndSpacesValidation(newData.getLastName()) &&
				Validator.phoneNumberValidation(newData.getPhoneNumber()) &&
				Validator.isValidAdress(newData.getStreet(), newData.getCity(),newData.getState());

	}

	@Override
	public Boolean isOldPasswordCorrect(String email, HashMap<String, String> data) throws InvalidPasswordException {
		MyUser currentUser = userRepository.findByEmail(email);
		String newPasswordHash = passwordEncoder.encode(data.get("newPassword1"));
		if (!data.get("newPassword1").equals("") && data.get("newPassword1").equals(data.get("newPassword2")) && passwordEncoder.matches(data.get("oldPassword"), currentUser.getPassword())) {
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
		if(instructor != null){
			Address address = instructor.getAddress();
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

	@Override
	public void changeInstrctorData(InstructorNewDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException {
		UserProfileData data = new UserProfileData(newData.getEmail(), newData.getFirstName(), newData.getLastName(), newData.getPhoneNumber(),
				newData.getStreet(), newData.getCity(), newData.getState());
		boolean validation = validateUserNewData(data);
		if(validation){
			MyUser user = userRepository.findByEmail(newData.getEmail());
			Optional<Instructor> instr = instructorRepository.findById(user.getId());
			if(instr.isPresent()){
				Instructor instructor = instr.get();
				instructor.setFirstName(newData.getFirstName());
				instructor.setLastName(newData.getLastName());
				instructor.setPhoneNumber(newData.getPhoneNumber());
				Address newAddress = new Address(newData.getStreet(), newData.getCity(), newData.getState());
				addressRepository.save(newAddress);
				instructor.setAddress(newAddress);
				userRepository.save(instructor);
			}
		}
	}

	private boolean instructorDataValidation(InstructorNewDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException {
		return  Validator.phoneNumberValidation(newData.getPhoneNumber()) &&
				Validator.onlyLetterAndSpacesValidation(newData.getFirstName()) &&
				Validator.onlyLetterAndSpacesValidation(newData.getLastName()) &&
				Validator.isValidAdress(newData.getStreet(), newData.getCity(), newData.getStreet());
	}

	@Override
	public Instructor findInstructorByEmail(String email){
		MyUser user = userRepository.findByEmail(email);
		Optional<Instructor> instructor = instructorRepository.findById(user.getId());
		return instructor.orElse(null);
	}

	@Override
	public void changeCottageOwnerData(CottageOwnerNewDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException {
		UserProfileData data = new UserProfileData(newData.getEmail(), newData.getFirstName(), newData.getLastName(), newData.getPhoneNumber(),
				newData.getStreet(), newData.getCity(), newData.getState());
		boolean validation = validateUserNewData(data);
		if(validation){
			MyUser user = userRepository.findByEmail(newData.getEmail());
			Optional<CottageOwner> cottageOwner = cottageOwnerRepository.findById(user.getId());
			if(cottageOwner.isPresent()){
				CottageOwner owner = cottageOwner.get();
				owner.setFirstName(newData.getFirstName());
				owner.setLastName(newData.getLastName());
				owner.setPhoneNumber(newData.getPhoneNumber());
				Address newAddress = new Address(newData.getStreet(), newData.getCity(), newData.getState());
				addressRepository.save(newAddress);
				owner.setAddress(newAddress);
				userRepository.save(owner);
			}

		}
	}
	@Override
	public void changeShipOwnerData(ShipOwnerNewDataDTO newData) throws OnlyLettersAndSpacesException, InvalidPhoneNumberException, InvalidAddressException {
		UserProfileData data = new UserProfileData(newData.getEmail(), newData.getFirstName(), newData.getLastName(), newData.getPhoneNumber(),
				newData.getStreet(), newData.getCity(), newData.getState());
		boolean validation = validateUserNewData(data);
		if(validation){
			MyUser user = userRepository.findByEmail(newData.getEmail());
			Optional<ShipOwner> shipOwner = shipOwnerRepository.findById(user.getId());
			if(shipOwner.isPresent()){
				ShipOwner owner = shipOwner.get();
				owner.setFirstName(newData.getFirstName());
				owner.setLastName(newData.getLastName());
				owner.setPhoneNumber(newData.getPhoneNumber());
				Address newAddress = new Address(newData.getStreet(), newData.getCity(), newData.getState());
				addressRepository.save(newAddress);
				owner.setAddress(newAddress);
				userRepository.save(owner);
			}

		}
	}
	@Override
	@Transactional
	public boolean sendDeleteRequestCottageOwner(String email, String reason) {
		MyUser user = userRepository.findByEmail(email);
		List<Reservation> listOfReservation = reservationRepository.findByCottageOwnerEmail(email);
		LocalDate today = LocalDate.now();
		for(Reservation r:listOfReservation){
			if((today.compareTo(r.getEndDate())<0)){
				return false;
			}
		}
		DeleteRequest deleteRequest = new DeleteRequest(reason, user);
		deleteRequestRepository.save(deleteRequest);
		return true;
	}

	@Override
	@Transactional
	public boolean sendDeleteRequestShipOwner(String email, String reason) {
		MyUser user = userRepository.findByEmail(email);
		List<Reservation> listOfReservation = reservationRepository.findByShipOwnerEmail(email);
		LocalDate today = LocalDate.now();
		for(Reservation r:listOfReservation){
			if((today.compareTo(r.getEndDate())<0)){
				return false;
			}
		}
		DeleteRequest deleteRequest = new DeleteRequest(reason, user);
		deleteRequestRepository.save(deleteRequest);
		return true;
	}

}
