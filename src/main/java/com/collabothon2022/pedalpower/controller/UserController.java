package com.collabothon2022.pedalpower.controller;

import com.collabothon2022.pedalpower.persistence.model.User;
import com.collabothon2022.pedalpower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public User getUser(@RequestParam("email") String email) {
		User user = userService.get(email);
		user.setCurrentTrip(null);
		return user;
	}
}
