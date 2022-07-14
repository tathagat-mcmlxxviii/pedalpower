package com.collabothon2022.pedalpower.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.collabothon2022.pedalpower.BaseSpringTest;
import com.collabothon2022.pedalpower.external.api.city.ExchangeEntry;
import com.collabothon2022.pedalpower.external.api.city.ExchangeResponse;
import com.collabothon2022.pedalpower.persistence.model.City;
import com.collabothon2022.pedalpower.persistence.model.User;
import com.collabothon2022.pedalpower.persistence.repository.CityRepository;
import com.collabothon2022.pedalpower.persistence.repository.UserRepository;

public class UserServiceTest extends BaseSpringTest {
	@Autowired
	private UserService testee;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Test
	public void shouldAutowire() {
		assertNotNull(testee);
	}
	
	@Test
	public void shouldFindByEmail() {
		// given
		String email = "email@test.com";
		User user = new User("first name", "last name", email);
		user = userRepository.save(user);
		
		// when
		User found = testee.get(email);
		
		// then
		assertEquals(user.getId(), found.getId());
	}
	
	@Test
	public void shouldUpdateCity() {
		// given - using different email here to avoid duplicate email
		User user = new User("first name", "last name", "email2@test.com");
		City city = new City("name1", null);
		city = cityRepository.save(city);
		user.setCity(city);
		user = userRepository.save(user);
		assertEquals(city, user.getCity());
		
		// when
		String newCityName = "name2";
		City city2 = new City(newCityName, null);
		city2 = cityRepository.save(city2);
		user.setCity(city2);
		user = testee.updateCity(user, newCityName);
		
		// then
		assertEquals(city2, user.getCity());
	}
	
	@Test
	public void shouldStartTrip() {
		// given
		User user = new User("first name", "last name", "email3@test.com");
		user = userRepository.save(user);
		assertNull(user.getCurrentTripUuid());
		assertNull(user.getCurrentTrip());
		
		// when
		user = testee.startTrip(user);
		
		// then
		assertNotNull(user.getCurrentTripUuid());
		assertNotNull(user.getCurrentTrip());
	}
	
	@Test
	public void shouldEndTrip() {
		// given
		User user = new User("first name", "last name", "email3@test.com");
		user = userRepository.save(user);
		user = testee.startTrip(user);
		assertNotNull(user.getCurrentTripUuid());
		assertNotNull(user.getCurrentTrip());
		assertEquals(0, user.getPoints());
		
		// when
		user = testee.endTrip(user);
		
		// then
		assertNull(user.getCurrentTripUuid());
		assertNull(user.getCurrentTrip());
		assertNotEquals(0, user.getPoints());
	}
	
	@Test
	public void shouldUpdateEndpoint() {
		// given
		User user = new User("first name", "last name", "email3@test.com");
		user = userRepository.save(user);
		user = testee.startTrip(user);
		String newGpsEndpoint = "asdf-fdsa";
		assertNull(user.getCurrentTrip().getDatapoints());
		
		// when
		user = testee.updateTrip(user, newGpsEndpoint);
		
		// then
		assertTrue(user.getCurrentTrip().getDatapoints().contains(newGpsEndpoint));
	}
	
	@Test
	public void shouldGetBuyingOptions() {
		// given
		User user = new User("first name", "last name", "email4@test.com");
		user = userRepository.save(user);
		
		// when
		List<ExchangeEntry> exchangeEntries = testee.getBuyingOptions(user);
		
		// then
		assertFalse(exchangeEntries.isEmpty());
	}
	
	@Test
	public void shouldBuy() {
		// given
		User user = new User("first name", "last name", "email5@test.com");
		user.setPoints(100);
		user = userRepository.save(user);
		List<ExchangeEntry> exchangeEntries = testee.getBuyingOptions(user);
		assertTrue(user.getPurchaseHistory().isEmpty());
		
		// when
		ExchangeEntry exchangeEntryBuyingOption = exchangeEntries.get(0);
		ExchangeResponse exchangeResponse = testee.buy(user, exchangeEntryBuyingOption);
		
		// then
		assertNotNull(exchangeResponse.getBase64TicketImg());
		assertEquals(100-exchangeEntryBuyingOption.getPointValue(), user.getPoints());
	}
	
	@Test
	public void shouldLoadPurchaseHistory() {
		// given
		User user = new User("first name", "last name", "email5@test.com");
		user = userRepository.save(user);
		List<ExchangeEntry> exchangeEntries = testee.getBuyingOptions(user);
		assertTrue(user.getPurchaseHistory().isEmpty());
		testee.buy(user, exchangeEntries.get(0));
		assertTrue(user.getPurchaseHistory().isEmpty());
		
		// when
		user = testee.loadPurchaseHistory(user);
		
		// then
		assertFalse(user.getPurchaseHistory().isEmpty());
	}
}
