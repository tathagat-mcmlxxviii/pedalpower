package com.collabothon2022.pedalpower.distance;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

@Ignore
public class GpsDistanceTest {
	private final DistanceApi testee = new GpsDistance();

	private final List<String> dataPoints = new ArrayList<>();

	@Test
	public void shouldCalculateDistance() {
		// given
		dataPoints.add("51.764100,19.412342");
		dataPoints.add("51.767328,19.421475");
		dataPoints.add("51.755738,19.430354");

		String coordinates = dataPoints.stream()
				.reduce((s, s2) -> String.join(";", s, s2))
				.orElseThrow(() -> new IllegalStateException("cannot join coordinates"));
		// when
		double distanceInKm = testee.getDistanceInKm(coordinates);

		//then
		Assertions.assertNotEquals(0,distanceInKm);
		Assertions.assertEquals(2.1, distanceInKm, 0.06);
	}
}
