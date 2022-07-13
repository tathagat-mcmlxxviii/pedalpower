package com.collabothon2022.pedalpower.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabothon2022.pedalpower.external.api.CityInfoApi;
import com.collabothon2022.pedalpower.external.api.ExchangeEntry;
import com.collabothon2022.pedalpower.external.api.ExchangeRequest;
import com.collabothon2022.pedalpower.external.api.ExchangeResponse;
import com.collabothon2022.pedalpower.persistence.model.City;
import com.collabothon2022.pedalpower.persistence.model.PurchaseHistory;
import com.collabothon2022.pedalpower.persistence.model.Trip;
import com.collabothon2022.pedalpower.persistence.model.User;
import com.collabothon2022.pedalpower.persistence.repository.CityRepository;
import com.collabothon2022.pedalpower.persistence.repository.PurchaseHistoryRepository;
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
	
	@Autowired
	private CityInfoApi cityInfoApi;
	
	@Autowired
	private PurchaseHistoryRepository purchaseHistoryRepository;

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
		
		// TODO
		// call google API to get distance in km based on datapoints
		
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

	@Override
	public List<ExchangeEntry> getBuyingOptions(User user) {
		return cityInfoApi.getExchangeOptionList(user.getCity());
	}

	@Override
	public ExchangeResponse buy(User user, ExchangeEntry exchangeEntry) {
		ExchangeRequest request = new ExchangeRequest(user.getId(), exchangeEntry.getBuyUrl());
		ExchangeResponse executeExchangeResponse = cityInfoApi.executeExchange(request);
		
		PurchaseHistory purchaseHistory = new PurchaseHistory(exchangeEntry.getBuyUrl(), exchangeEntry.getPointValue(), executeExchangeResponse.getBase64TicketImg(), user);
		purchaseHistoryRepository.save(purchaseHistory);
		
		int currentPoints = user.getPoints();
		int newPoints = currentPoints - exchangeEntry.getPointValue();
		user.setPoints(newPoints);
		user = userRepository.save(user);
		
		return executeExchangeResponse;
	}

	@Override
	public User loadPurchaseHistory(User user) {
		List<PurchaseHistory> purchaseHistories = purchaseHistoryRepository.findByUser(user).get();
		user.setPurchaseHistory(purchaseHistories);
		return user;
	}

}
