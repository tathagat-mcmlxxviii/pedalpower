package com.collabothon2022.pedalpower.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

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
	
	@Test
	public void shoudFindByEmail() {
		// given
		String email = "test2@test.com";
		User user = new User("first name", "last name", email);
		user = testee.save(user);
		
		// when
		Optional<User> found = testee.findByEmail(email);
		
		// then
		assertTrue(found.isPresent());
		assertEquals(user.getId(), found.get().getId());
	}
}
