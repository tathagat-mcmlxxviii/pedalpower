package com.collabothon2022.pedalpower.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.collabothon2022.pedalpower.BaseSpringTest;
import com.collabothon2022.pedalpower.persistence.model.User;

public class UserRepositoryTest extends BaseSpringTest {
	@Autowired
	private UserRepository testee;
	
	@Test
	public void shoudAutowire() {
		assertNotNull(testee);
	}
	
	@Test
	public void shoudCRUD() {
		// create
		User user = new User("first name", "last name", "test@test.com");
		assertNull(user.getId());
		
		user = testee.save(user);
		Long userId = user.getId();
		assertNotNull(userId);
		
		// read
		User found = testee.findById(userId).get();
		assertEquals(user, found);
		
		// update
		String newFirstName = "new first name";
		found.setFirstName(newFirstName);
		found = testee.save(found);
		assertEquals(newFirstName, found.getFirstName());
		
		// delete
		testee.delete(found);
		assertFalse(testee.findById(userId).isPresent());
	}
}
