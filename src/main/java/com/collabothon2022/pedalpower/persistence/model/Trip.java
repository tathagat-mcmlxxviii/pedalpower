package com.collabothon2022.pedalpower.persistence.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@EqualsAndHashCode.Include
	private UUID uuid;
	@ManyToOne
	private MeansOfTransport meansOfTransport;
	// semicolon separated GPS datapoints
	private String datapoints;
	// rounded off km for now
	private int km;
	@ManyToOne
	private User user;
	
	public Trip() {
		this.uuid = UUID.randomUUID();
	}
}
