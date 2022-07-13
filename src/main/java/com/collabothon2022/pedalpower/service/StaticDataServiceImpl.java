package com.collabothon2022.pedalpower.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabothon2022.pedalpower.persistence.model.City;
import com.collabothon2022.pedalpower.persistence.model.MeansOfTransport;
import com.collabothon2022.pedalpower.persistence.repository.CityRepository;
import com.collabothon2022.pedalpower.persistence.repository.MeansOfTransportRepository;

@Service
public class StaticDataServiceImpl implements StaticDataService {

	@Autowired
	private MeansOfTransportRepository meansOfTransportRepository;
	
	@Autowired
	private CityRepository cityRepository;

	@Override
	public List<MeansOfTransport> getMeansOfTransport() {
		Iterable<MeansOfTransport> allMeansOfTransport = meansOfTransportRepository.findAll();
		return StreamSupport.stream(allMeansOfTransport.spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public List<City> getCities() {
		Iterable<City> allCities = cityRepository.findAll();
		return StreamSupport.stream(allCities.spliterator(), false).collect(Collectors.toList());
	}
}
