package com.collabothon2022.pedalpower.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.collabothon2022.pedalpower.persistence.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
