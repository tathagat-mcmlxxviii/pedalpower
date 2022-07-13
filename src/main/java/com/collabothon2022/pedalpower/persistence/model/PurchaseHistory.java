package com.collabothon2022.pedalpower.persistence.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PurchaseHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private LocalDateTime timestamp = LocalDateTime.now();
	private String buyUrl;
	private int pointCost;

	@Lob
	private String base64OfTicket;

	@ManyToOne
	private User user;

	public PurchaseHistory(String buyUrl, int pointCost, String base64OfTicket, User user) {
		this.buyUrl = buyUrl;
		this.pointCost = pointCost;
		this.base64OfTicket = base64OfTicket;
		this.user = user;
	}
}
