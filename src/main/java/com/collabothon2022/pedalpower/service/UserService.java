package com.collabothon2022.pedalpower.service;

import com.collabothon2022.pedalpower.persistence.model.User;

public interface UserService {
	/**
	 * Get user from email
	 * @param email
	 * @return
	 */
	User get(String email);
	
	/**
	 * Update the city of the user
	 * @param cityName
	 * @return
	 */
	User updateCity(User user, String cityName);
	
	/**
	 * Starts the trip.
	 * Sets uuid of the trip in the user table
	 * @param user
	 * @return
	 */
	User startTrip(User user);
	
	/**
	 * Ends trip.
	 * Calculate km.
	 * Sets null in trip uud in user table.
	 * @param user
	 * @return
	 */
	User endTrip(User user);
	
	/**
	 * Adds the new GPS endpoint to the active trip.
	 * Obviously there has to be an active trip.
	 * @param user
	 * @param newGpsEndpoint
	 * @return
	 */
	User updateTrip(User user, String newGpsEndpoint);
}
