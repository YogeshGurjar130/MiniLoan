package com.example.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.loan.pojo.UserPojo;
import com.example.loan.services.UserService;

@RestController
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserPojo userPojo) {
        return userService.loginUser(userPojo);
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserPojo userPojo) {
		String response = userService.createUser(userPojo);
		return ResponseEntity.ok(response);
	}
}
