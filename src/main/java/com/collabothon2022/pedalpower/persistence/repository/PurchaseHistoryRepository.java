package com.collabothon2022.pedalpower.persistence.repository;

import com.collabothon2022.pedalpower.persistence.model.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
}
