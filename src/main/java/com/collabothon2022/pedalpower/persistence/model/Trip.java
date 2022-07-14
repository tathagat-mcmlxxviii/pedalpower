package com.collabothon2022.pedalpower.persistence.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

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
	@Type(type="org.hibernate.type.UUIDCharType")
	private UUID uuid;
	@ManyToOne
	private MeansOfTransport meansOfTransport;
	// semicolon separated GPS datapoints
	@Lob
	private String datapoints;
	// rounded off km for now
	private int km;
	@ManyToOne
	private User user;
	private LocalDateTime startTimestamp;
	private LocalDateTime endTimestamp;
	
	public Trip() {
		this.uuid = UUID.randomUUID();
		this.startTimestamp = LocalDateTime.now();
	}
}
