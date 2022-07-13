package com.collabothon2022.pedalpower.controller;

import com.collabothon2022.pedalpower.persistence.model.MeansOfTransport;
import com.collabothon2022.pedalpower.service.MeansOfTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {

	private final MeansOfTransportService meansOfTransportService;

	@Autowired
	public CityController(MeansOfTransportService meansOfTransportService) {
		this.meansOfTransportService = meansOfTransportService;
	}

	@GetMapping(value = "transport", produces = "application/json")
	public List<MeansOfTransport> getMeansOfTransport() {
		return meansOfTransportService.getMeansOfTransport();
	}

}
