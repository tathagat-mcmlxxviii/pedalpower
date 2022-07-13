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
	User updateCity(String cityName);
}
