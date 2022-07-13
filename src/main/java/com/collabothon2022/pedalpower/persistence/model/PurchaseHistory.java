package com.collabothon2022.pedalpower.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

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
	private int optionId;
	private int pointCost;

	@Lob
	private String base64OfTicket;

	@ManyToOne
	private User user;

	public PurchaseHistory(int optionId, int pointCost, String base64OfTicket, User user) {
		this.optionId = optionId;
		this.pointCost = pointCost;
		this.base64OfTicket = base64OfTicket;
		this.user = user;
	}
}
