package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.InstructorProfileData;
import com.booking.ISAbackend.dto.UserProfileData;
import com.booking.ISAbackend.model.Address;
import com.booking.ISAbackend.model.Instructor;
import com.booking.ISAbackend.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private UserService userService;
	
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
}
