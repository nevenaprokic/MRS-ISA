package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.InstructorProfileData;
import com.booking.ISAbackend.dto.OwnerRegistrationRequest;
import com.booking.ISAbackend.dto.UserProfileData;
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
	public ResponseEntity<UserProfileData> getInstructorProfileInfo(@RequestParam String email){
		//odraditi autentifikaciju i autorizaciju


		MyUser myUser =  userService.findInstructorByEmail(email);
//		Instructor instructor = new Instructor();
		Address address = myUser.getAddress();
		System.out.println(address.getCity());
		UserProfileData data = new UserProfileData(myUser.getEmail(),
																myUser.getFirstName(),
																myUser.getLastName(),
																myUser.getPhoneNumber(),
																myUser.getAddress().getStreet(),
																myUser.getAddress().getCity(),
																myUser.getAddress().getState()
																);
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
}
