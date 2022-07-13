package com.collabothon2022.pedalpower.persistence.repository;

import com.collabothon2022.pedalpower.BaseSpringTest;
import com.collabothon2022.pedalpower.persistence.model.PurchaseHistory;
import com.collabothon2022.pedalpower.persistence.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class PurchaseHistoryRepositoryTest extends BaseSpringTest {

	@Autowired
	PurchaseHistoryRepository testee;

	@Autowired
	UserRepository userRepository;

	private Long testUserId;

	@BeforeEach
	public void beforeAll() {
		if (testUserId == null) {
			User testUser = new User("test", "test", "test@test.com");
			User savedUser = userRepository.save(testUser);
			testUserId = savedUser.getId();
		}
	}

	@Test
	public void shouldCreatePurchaseHistory() {
		// given
		User user = userRepository.findById(testUserId).orElseThrow(() -> new IllegalStateException("no user"));
		PurchaseHistory purchaseHistory = new PurchaseHistory(1, 10, "ticket", user);

		// when
		PurchaseHistory saved = testee.save(purchaseHistory);

		// then
		PurchaseHistory fromDb = testee.findById(saved.getId()).orElse(null);
		Assertions.assertNotNull(fromDb, "Failed save");
		Assertions.assertEquals("ticket", fromDb.getBase64OfTicket());
		Assertions.assertEquals(1, fromDb.getOptionId());
		Assertions.assertEquals(10, fromDb.getPointCost());
	}

}
