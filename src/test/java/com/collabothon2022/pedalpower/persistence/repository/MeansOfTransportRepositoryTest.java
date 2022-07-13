package com.collabothon2022.pedalpower.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.collabothon2022.pedalpower.BaseSpringTest;
import com.collabothon2022.pedalpower.persistence.model.MeansOfTransport;

public class MeansOfTransportRepositoryTest extends BaseSpringTest {
	@Autowired
	private MeansOfTransportRepository testee;
	
	@Test
	public void shoudAutowire() {
		assertNotNull(testee);
	}
	
	@Test
	public void shoudCRUD() {
		// create
		MeansOfTransport meansOfTransport = new MeansOfTransport("MeansOfTransport name");
		assertNull(meansOfTransport.getId());
		
		meansOfTransport = testee.save(meansOfTransport);
		Long meansOfTransportId = meansOfTransport.getId();
		assertNotNull(meansOfTransportId);
		
		// read
		MeansOfTransport found = testee.findById(meansOfTransportId).get();
		assertEquals(meansOfTransport, found);
		
		// update
		String newFirstName = "new MeansOfTransport name";
		found.setName(newFirstName);
		found = testee.save(found);
		assertEquals(newFirstName, found.getName());
		
		// delete
		testee.delete(found);
		assertFalse(testee.findById(meansOfTransportId).isPresent());
	}
}
