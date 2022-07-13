package com.collabothon2022.pedalpower.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabothon2022.pedalpower.persistence.model.City;
import com.collabothon2022.pedalpower.persistence.model.Trip;
import com.collabothon2022.pedalpower.persistence.model.User;
import com.collabothon2022.pedalpower.persistence.repository.CityRepository;
import com.collabothon2022.pedalpower.persistence.repository.TripRepository;
import com.collabothon2022.pedalpower.persistence.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private TripRepository tripRepository;

	@Override
	public User get(String email) {
		return userRepository.findByEmail(email).get();
	}

	@Override
	public User updateCity(User user, String cityName) {
		City city = cityRepository.findByName(cityName).get();
		user.setCity(city);
		user = userRepository.save(user);
		return user;
	}

	@Override
	public User startTrip(User user) {
		Trip trip = new Trip();
		trip.setUser(user);
		trip = tripRepository.save(trip);
		
		user.setCurrentTripUuid(trip.getUuid());
		user = userRepository.save(user);
		
		user.setCurrentTrip(trip);
		
		return user;
	}

	@Override
	public User endTrip(User user) {
		UUID currentTripUuid = user.getCurrentTripUuid();
		Trip currentTrip = tripRepository.findByUuid(currentTripUuid).get();
		currentTrip.setEndTimestamp(LocalDateTime.now());
		currentTrip = tripRepository.save(currentTrip);
		
		user.setCurrentTripUuid(null);
		user = userRepository.save(user);
		
		return user;
	}

	@Override
	public User updateTrip(User user, String newGpsEndpoint) {
		UUID currentTripUuid = user.getCurrentTripUuid();
		Trip currentTrip = tripRepository.findByUuid(currentTripUuid).get();
		
		String existingDataEndpoints = currentTrip.getDatapoints();
		String newDataEndpoints = existingDataEndpoints + ";" + newGpsEndpoint;
		currentTrip.setDatapoints(newDataEndpoints);
		currentTrip = tripRepository.save(currentTrip);
		
		user.setCurrentTrip(currentTrip);
		
		return user;
	}

}
