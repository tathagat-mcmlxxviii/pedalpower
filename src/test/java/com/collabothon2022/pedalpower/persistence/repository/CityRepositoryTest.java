package com.collabothon2022.pedalpower.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.collabothon2022.pedalpower.BaseSpringTest;
import com.collabothon2022.pedalpower.persistence.model.City;

public class CityRepositoryTest extends BaseSpringTest {
	@Autowired
	private CityRepository testee;
	
	@Test
	public void shoudAutowire() {
		assertNotNull(testee);
	}
	
	@Test
	public void shoudCRUD() {
		// create
		City city = new City("city name", "info endpoint");
		assertNull(city.getId());
		
		city = testee.save(city);
		Long cityId = city.getId();
		assertNotNull(cityId);
		
		// read
		City found = testee.findById(cityId).get();
		assertEquals(city, found);
		
		// update
		String newFirstName = "new city name";
		found.setCityName(newFirstName);
		found = testee.save(found);
		assertEquals(newFirstName, found.getCityName());
		
		// delete
		testee.delete(found);
		assertFalse(testee.findById(cityId).isPresent());
	}
}
