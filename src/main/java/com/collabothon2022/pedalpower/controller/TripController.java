package com.collabothon2022.pedalpower.controller;

import com.collabothon2022.pedalpower.persistence.model.Trip;
import com.collabothon2022.pedalpower.persistence.model.User;
import com.collabothon2022.pedalpower.service.UserService;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/trip")
public class TripController {

	private UserService userService;

	@Autowired
	public TripController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "start", produces = "application/json")
	public Trip startTrip(@RequestParam("email") String email) {
		User user = userService.get(email);
		user = userService.startTrip(user);
		Trip currentTrip = user.getCurrentTrip();
		currentTrip.setUser(null);
		return currentTrip;
	}

	@GetMapping(value = "current", produces = "application/json")
	public Trip getCurrentTrip(@RequestParam("email") String email) {
		User user = userService.get(email);
		UUID currentTripUuid = user.getCurrentTripUuid();
		if(currentTripUuid != null){
			return userService.getTrip(currentTripUuid);
		}
		return null;
	}

	@GetMapping(value = "end", produces = "application/json")
	public User endTrip(@RequestParam("email") String email) {
		User user = userService.get(email);
		user = userService.endTrip(user);
		return user;
	}

	@PutMapping(produces = "application/json", consumes = "application/json")
	public Trip updateTrip(@RequestBody UpdateRequest updateRequest) {
		User user = userService.get(updateRequest.getEmail());
		user = userService.updateTrip(user, updateRequest.getDataPoint());
		Trip currentTrip = user.getCurrentTrip();
		currentTrip.setUser(null);
		return currentTrip;
	}

	static class UpdateRequest{
		private String email;
		private String dataPoint;

		public UpdateRequest() {
		}

		public String getDataPoint() {
			return dataPoint;
		}

		public void setDataPoint(String dataPoint) {
			this.dataPoint = dataPoint;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
}
