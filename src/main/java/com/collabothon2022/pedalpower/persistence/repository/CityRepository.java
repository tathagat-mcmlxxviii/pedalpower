package com.collabothon2022.pedalpower.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.collabothon2022.pedalpower.persistence.model.City;

public interface CityRepository extends CrudRepository<City, Long> {
	Optional<City> findByName(String name);
}
