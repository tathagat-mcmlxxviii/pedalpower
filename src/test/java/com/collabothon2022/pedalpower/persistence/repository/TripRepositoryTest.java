package com.collabothon2022.pedalpower.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.collabothon2022.pedalpower.BaseSpringTest;
import com.collabothon2022.pedalpower.persistence.model.Trip;
import com.collabothon2022.pedalpower.persistence.model.User;

public class TripRepositoryTest extends BaseSpringTest {
	@Autowired
	private TripRepository testee;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void shoudAutowire() {
		assertNotNull(testee);
	}
	
	@Test
	public void shoudCreateMultipleTripsForUser() {
		// given
		User user = new User("first name", "last name", "email");
		user = userRepository.save(user);
		Trip trip1 = new Trip();
		trip1.setUser(user);
		
		Trip trip2 = new Trip();
		trip2.setUser(user);
		
		// when
		testee.save(trip1);
		testee.save(trip2);
		
		// then
		assertEquals(2, testee.findByUser(user).get().size());
	}
}
