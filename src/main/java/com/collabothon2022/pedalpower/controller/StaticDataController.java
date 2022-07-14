package com.collabothon2022.pedalpower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collabothon2022.pedalpower.persistence.model.City;
import com.collabothon2022.pedalpower.persistence.model.MeansOfTransport;
import com.collabothon2022.pedalpower.service.StaticDataService;

@RestController
@RequestMapping("/staticdata")
public class StaticDataController {

	@Autowired
	private StaticDataService staticDataService;

	@GetMapping(value = "/transport", produces = "application/json")
	public List<MeansOfTransport> getMeansOfTransport() {
		return staticDataService.getMeansOfTransport();
	}
	
	@GetMapping(value = "/cities", produces = "application/json")
	public List<City> getCities() {
		return staticDataService.getCities();
	}

}
