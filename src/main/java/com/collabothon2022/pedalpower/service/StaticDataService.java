package com.collabothon2022.pedalpower.service;

import java.util.List;

import com.collabothon2022.pedalpower.persistence.model.City;
import com.collabothon2022.pedalpower.persistence.model.MeansOfTransport;

/**
 * Get static data like city etc
 * @author tathagat
 *
 */
public interface StaticDataService {
    List<MeansOfTransport> getMeansOfTransport();
    
    List<City> getCities();
}
