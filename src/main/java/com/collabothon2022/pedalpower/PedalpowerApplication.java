package com.collabothon2022.pedalpower;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.collabothon2022.pedalpower.persistence.model.City;
import com.collabothon2022.pedalpower.persistence.model.MeansOfTransport;
import com.collabothon2022.pedalpower.persistence.repository.CityRepository;
import com.collabothon2022.pedalpower.persistence.repository.MeansOfTransportRepository;

@SpringBootApplication
public class PedalpowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedalpowerApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadMeansOfTransport(MeansOfTransportRepository repository) {
		return (args) -> {
			List<MeansOfTransport> initialMeansOfTransportData = new ArrayList<>();

			initialMeansOfTransportData.add(new MeansOfTransport("Unkown"));
			initialMeansOfTransportData.add(new MeansOfTransport("Cycling"));
			initialMeansOfTransportData.add(new MeansOfTransport("Walking"));
			initialMeansOfTransportData.add(new MeansOfTransport("Using e-Scooter"));

			repository.saveAll(initialMeansOfTransportData);
		};
	}
	
	@Bean
	public CommandLineRunner loadCities(CityRepository repository) {
		return (args) -> {
			List<City> cities = new ArrayList<>();

			cities.add(new City("Lodz", "lodzInfoEndpoint"));
			cities.add(new City("Frankfurt", "frankfurtInfoEndpoint"));
			cities.add(new City("London", "londonInfoEndpoint"));
			cities.add(new City("Paris", "parisInfoEndpoint"));
			cities.add(new City("New York", "nyInfoEndpoint"));

			repository.saveAll(cities);
		};
	}

}
