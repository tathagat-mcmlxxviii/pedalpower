package com.collabothon2022.pedalpower.service;

import com.collabothon2022.pedalpower.persistence.model.MeansOfTransport;
import com.collabothon2022.pedalpower.persistence.repository.MeansOfTransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MeansOfTransportServiceImpl implements MeansOfTransportService {

	private final MeansOfTransportRepository meansOfTransportRepository;

	@Autowired
	public MeansOfTransportServiceImpl(MeansOfTransportRepository meansOfTransportRepository) {
		this.meansOfTransportRepository = meansOfTransportRepository;
	}

	@Override
	public List<MeansOfTransport> getMeansOfTransport() {
		Iterable<MeansOfTransport> allMeansOfTransport = meansOfTransportRepository.findAll();
		return StreamSupport.stream(allMeansOfTransport.spliterator(), false).collect(Collectors.toList());
	}

	@PostConstruct
	public void postConstruct(){
		List<MeansOfTransport> seed = new ArrayList<>();

		seed.add(new MeansOfTransport("Cycling"));
		seed.add(new MeansOfTransport("Walking"));
		seed.add(new MeansOfTransport("Using e-Scooter"));

		meansOfTransportRepository.saveAll(seed);
	}
}
