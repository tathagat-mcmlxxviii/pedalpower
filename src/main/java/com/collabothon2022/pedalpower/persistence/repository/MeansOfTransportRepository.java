package com.collabothon2022.pedalpower.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.collabothon2022.pedalpower.persistence.model.City;
import com.collabothon2022.pedalpower.persistence.model.MeansOfTransport;

public interface MeansOfTransportRepository extends CrudRepository<MeansOfTransport, Long> {

}
