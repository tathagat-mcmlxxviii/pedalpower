package com.collabothon2022.pedalpower.service;

import java.util.List;
import java.util.UUID;

import com.collabothon2022.pedalpower.external.api.city.ExchangeEntry;
import com.collabothon2022.pedalpower.external.api.city.ExchangeResponse;
import com.collabothon2022.pedalpower.persistence.model.Trip;
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
	
	/**
	 * Get all possible buying options.
	 * Depends on city of the user
	 * @param user
	 * @return
	 */
	List<ExchangeEntry> getBuyingOptions(User user);
	
	/**
	 * Buy the ticket using city api.
	 * Returns the ticket.
	 * Reduces the points.
	 * @param user
	 * @param exchangeEntry
	 * @return
	 */
	ExchangeResponse buy(User user, ExchangeEntry exchangeEntry);
	
	/**
	 * Load all previous purchases
	 * @param user
	 * @return
	 */
	User loadPurchaseHistory(User user);

	Trip getTrip(UUID currentTripUuid);

}
