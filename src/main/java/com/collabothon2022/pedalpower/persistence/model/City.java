package com.collabothon2022.pedalpower.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@EqualsAndHashCode.Include
	private String cityName;
	// info endpoint will provide ticket info + buying endpoints
	private String infoEndpoint;
	
	public City(String cityName, String infoEndpoint) {
		this.cityName = cityName;
		this.infoEndpoint = infoEndpoint;
	}
}
