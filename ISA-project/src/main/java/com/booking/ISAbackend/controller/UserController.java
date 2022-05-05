package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.*;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPasswordException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;
import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.service.RegistrationRequestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.booking.ISAbackend.service.UserService;

import java.util.HashMap;

@RestController
@RequestMapping
public class UserController {
	protected final Log LOGGER = LogFactory.getLog(getClass());
	@Autowired
	private UserService userService;

	@Autowired
	private RegistrationRequestService registrationRequestService;

	@GetMapping("instructorProfileInfo")
	@Transactional
	public ResponseEntity<InstructorProfileData> getInstructorProfileInfo(@RequestParam String email){
		//odraditi autentifikaciju i autorizaciju
		InstructorProfileData instructor =  userService.getInstructorDataByEmail(email);
		if(instructor != null){
			return ResponseEntity.ok(instructor);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);


	}
    @GetMapping("cottageOwnerProfileInfo")
    @Transactional
    public ResponseEntity<CottageOwnerProfileInfoDTO> getCottageOwnerProfileInfo(@RequestParam String email){
        //odraditi autentifikaciju i autorizaciju

		try{
			CottageOwner cottageOwner =  userService.findCottageOwnerByEmail(email);
			CottageOwnerProfileInfoDTO data = new CottageOwnerProfileInfoDTO(cottageOwner);
			return ResponseEntity.ok(data);
		}catch  (Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
    }
	@GetMapping("shipOwnerProfileInfo")
	@Transactional
	public ResponseEntity<ShipOwnerProfileInfoDTO> getShipOwnerProfileInfo(@RequestParam String email){
		//odraditi autentifikaciju i autorizaciju

		try{
			ShipOwner shipOwner =  userService.findShipOwnerByEmail(email);
			ShipOwnerProfileInfoDTO data = new ShipOwnerProfileInfoDTO(shipOwner);
			return ResponseEntity.ok(data);
		}catch  (Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("registrationOwner")
	public ResponseEntity<String> sendOwnerRegistration(@RequestBody OwnerRegistrationRequestDTO request){
		try {
			boolean userIsExists = registrationRequestService.save(request);
			if(userIsExists)
				return ResponseEntity.ok("You have successfully submitted your registration request!");
			else
				return new ResponseEntity<>("User  with this email alredy exists!", HttpStatus.BAD_REQUEST);
		}catch (Exception e){
			return new ResponseEntity<>("You haven't successfully submitted your registration request!", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("change-instructor-data")
	public ResponseEntity<String> changeInstructorData(@RequestBody InstructorNewDataDTO newData){
		try{
			userService.changeInstrctorData(newData);
			return ResponseEntity.ok("Successfully changed you data");
		} catch (OnlyLettersAndSpacesException | InvalidPhoneNumberException | InvalidAddressException  e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
	}

	@PostMapping("updatePassword")
	public ResponseEntity<String> updatePassword(@RequestParam String email, @RequestBody HashMap<String, String> data) {
		try{
			userService.isOldPasswordCorrect(email, data);
			return ResponseEntity.ok("Successfully changed password.");
		} catch (InvalidPasswordException e) {
			return ResponseEntity.status(400).body("Old password is not correct.");
		}
	}

	@GetMapping("admin-profile")
	public ResponseEntity<UserProfileData> getAdminProfileInfo(@RequestParam String email){
		try{
				UserProfileData adminData = userService.findAdminByEmail(email);
				return ResponseEntity.ok(adminData);
		}
		catch (Exception e){
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("change-admin-data")
	public ResponseEntity<String> changeAdminData(@RequestBody UserProfileData newData){
		try{
			userService.changeAdminData(newData);
			return ResponseEntity.ok("Successfully changed you data");
		} catch (OnlyLettersAndSpacesException | InvalidPhoneNumberException | InvalidAddressException  e) {
			e.printStackTrace();
			return ResponseEntity.status(400).body(e.getMessage());
		}
	}
	@PostMapping("changeCottageOwnerData")
	public ResponseEntity<String> changeCottageOwnerData(@RequestBody CottageOwnerNewDataDTO newData){
		try{
			userService.changeCottageOwnerData(newData);
			return ResponseEntity.ok("Successfully changed your data");
		} catch (OnlyLettersAndSpacesException | InvalidPhoneNumberException | InvalidAddressException  e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
	}
	@PostMapping("changeShipOwnerData")
	public ResponseEntity<String> changeShipOwnerData(@RequestBody ShipOwnerNewDataDTO newData){
		try{
			userService.changeShipOwnerData(newData);
			return ResponseEntity.ok("Successfully changed your data");
		} catch (OnlyLettersAndSpacesException | InvalidPhoneNumberException | InvalidAddressException  e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
	}

	@PostMapping("sendDeleteRequestCottageOwner")
	public ResponseEntity<String> sendDeleteRequestCottageOwner(@RequestParam String email, @RequestBody HashMap<String, String> data) {
		try{
			if(userService.sendDeleteRequestCottageOwner(email, data.get("reason")))
				return ResponseEntity.ok("Successfully created request to delete the order.");
			else
				return ResponseEntity.status(400).body("Unable to send request to delete the order, user's offers have reservations.");
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Unable to send request to delete the order.");
		}
	}

	@PostMapping("sendDeleteRequestShipOwner")
	public ResponseEntity<String> sendDeleteRequestShipOwner(@RequestParam String email, @RequestBody HashMap<String, String> data) {
		try{
			if(userService.sendDeleteRequestShipOwner(email, data.get("reason")))
				return ResponseEntity.ok("Successfully created request to delete the order.");
			else
				return ResponseEntity.status(400).body("Unable to send request to delete the order, user's offers have reservations.");
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Unable to send request to delete the order.");
		}
	}
}
