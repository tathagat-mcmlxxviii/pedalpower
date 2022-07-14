package com.collabothon2022.pedalpower.distance;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GpsDistance implements DistanceApi {

	@Override
	public double getDistanceInKm(String gpsPointData) {
		if(gpsPointData == null){
			return 0;
		}
		List<String[]> listOfCoordinates = parseGpsCoordinates(gpsPointData);
		double meters = 0.0;
		for (int i = 0; i < listOfCoordinates.size() - 1; i++) {
			meters += calculateDistanceHaversine(listOfCoordinates.get(i), listOfCoordinates.get(i + 1));
		}

		return meters / 1000;
	}

	/**
	 * Using haversine formula
	 *
	 * @link <a href="https://www.movable-type.co.uk/scripts/latlong.html">...</a>
	 */
	private double calculateDistanceHaversine(String[] coordinate1, String[] coordinate2) {
		double toRadiansCoeff = Math.PI / 180;
		double earthRadius = 6371e3;

		double lat1 = Double.parseDouble(coordinate1[0]);
		double lon1 = Double.parseDouble(coordinate1[1]);
		double lat2 = Double.parseDouble(coordinate2[0]);
		double lon2 = Double.parseDouble(coordinate2[1]);

		double lat1Rad = lat1 * toRadiansCoeff;
		double lat2Rad = lat2 * toRadiansCoeff;
		double latDiff = (lat2 - lat1) * toRadiansCoeff;
		double lonDiff = (lon2 - lon1) * toRadiansCoeff;

		double a = Math.pow(Math.sin(latDiff / 2), 2)
				+ Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(lonDiff / 2), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return earthRadius * c;
	}
}
