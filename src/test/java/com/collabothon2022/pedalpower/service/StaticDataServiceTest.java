package com.collabothon2022.pedalpower.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.collabothon2022.pedalpower.BaseSpringTest;

public class StaticDataServiceTest extends BaseSpringTest {
	@Autowired
	private StaticDataService testee;
	
	@Test
	public void shouldLoadDataOnStartUp() {
		assertFalse(testee.getCities().isEmpty());
		assertFalse(testee.getMeansOfTransport().isEmpty());
	}
}
