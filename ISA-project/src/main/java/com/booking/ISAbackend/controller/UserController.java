package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.InstructorProfileData;
import com.booking.ISAbackend.dto.NewOwnerDataDTO;
import com.booking.ISAbackend.dto.OwnerRegistrationRequest;
import com.booking.ISAbackend.dto.UserProfileData;
import com.booking.ISAbackend.exceptions.InvalidAddressException;
import com.booking.ISAbackend.exceptions.InvalidPhoneNumberException;
import com.booking.ISAbackend.exceptions.OnlyLettersAndSpacesException;
import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.Instructor;
import com.booking.ISAbackend.model.MyUser;
import com.booking.ISAbackend.model.RegistrationRequest;
import com.booking.ISAbackend.repository.RegistrationRequestRepository;
import com.booking.ISAbackend.service.RegistrationRequestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import com.booking.ISAbackend.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {
	protected final Log LOGGER = LogFactory.getLog(getClass());
	@Autowired
	private UserService userService;

	@Autowired
	private RegistrationRequestService registrationRequestService;
	
	@PostMapping("/user")
	public void doNothing() {
		return;
	}

	@GetMapping("/foo")
	public Map<String, String> getFoo() {
		Map<String, String> fooObj = new HashMap<>();
		fooObj.put("foo", "bar");
		return fooObj;
	}

	@GetMapping("instructorProfileInfo")
	@Transactional
	public ResponseEntity<InstructorProfileData> getInstructorProfileInfo(@RequestParam String email){
		//odraditi autentifikaciju i autorizaciju


		Instructor instructor =  userService.findInstructorByEmail(email);

		Address address = instructor.getAddress();
		InstructorProfileData data = new InstructorProfileData(instructor.getEmail(),
				instructor.getFirstName(),
				instructor.getLastName(),
				instructor.getPhoneNumber(),
				instructor.getAddress().getStreet(),
				instructor.getAddress().getCity(),
				instructor.getAddress().getState(),
				instructor.getOwnerCategory().toString(),
				instructor.getBiography());
		return ResponseEntity.ok(data);

	}
	@PostMapping("registrationOwner")
	public ResponseEntity<String> sendOwnerRegistration(@RequestBody OwnerRegistrationRequest request){
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

	@PostMapping("changeOwnerData")
	public ResponseEntity<String> changeOwnerData(@RequestBody NewOwnerDataDTO newData){
		try{
			userService.changeOwnerData(newData);
			return ResponseEntity.ok("Successfully changed you data");
		} catch (OnlyLettersAndSpacesException | InvalidPhoneNumberException | InvalidAddressException  e) {
			e.printStackTrace();
			return ResponseEntity.status(400).body(e.getMessage());

		}
	}
}
