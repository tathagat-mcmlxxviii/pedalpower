package com.collabothon2022.pedalpower.controller;

import com.collabothon2022.pedalpower.persistence.model.MeansOfTransport;
import com.collabothon2022.pedalpower.service.StaticDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {

	private final StaticDataService staticDataService;

	@Autowired
	public CityController(StaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	@GetMapping(value = "transport", produces = "application/json")
	public List<MeansOfTransport> getMeansOfTransport() {
		return staticDataService.getMeansOfTransport();
	}

}
