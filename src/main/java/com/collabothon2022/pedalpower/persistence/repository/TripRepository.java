package com.collabothon2022.pedalpower.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.collabothon2022.pedalpower.persistence.model.Trip;
import com.collabothon2022.pedalpower.persistence.model.User;

public interface TripRepository extends CrudRepository<Trip, Long> {
	Optional<List<Trip>> findByUser(User user);
	
	Optional<Trip> findByUuid(UUID uuid);
}
