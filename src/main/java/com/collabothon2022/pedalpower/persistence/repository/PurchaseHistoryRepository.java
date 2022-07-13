package com.collabothon2022.pedalpower.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collabothon2022.pedalpower.persistence.model.PurchaseHistory;
import com.collabothon2022.pedalpower.persistence.model.User;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
	Optional<List<PurchaseHistory>> findByUser(User user);
}
