package com.booking.ISAbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.ISAbackend.service.UserService;

import java.util.HashMap;
import java.util.Map;

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
}
