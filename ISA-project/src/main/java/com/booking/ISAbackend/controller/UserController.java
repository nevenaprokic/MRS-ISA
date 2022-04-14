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
	public ResponseEntity<InstructorProfileData> getInstructorProfileInfo(@RequestParam String email){
		//odraditi autentifikaciju i autorizaciju


		Instructor instructor =  userService.findInstructorByEmail(email);

		Address address = instructor.getAddress();
		System.out.println(address.getCity());
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
}
