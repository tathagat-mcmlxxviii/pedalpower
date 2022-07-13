package com.collabothon2022.pedalpower.persistence.model;


import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="APP_USER")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  @EqualsAndHashCode.Include
  private String email;
  private int points;
  // uuid of the active trip
  @Type(type="org.hibernate.type.UUIDCharType")
  private UUID currentTripUuid;
  @ManyToOne
  private City city;
  private transient Trip currentTrip;

  @OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY)
  private List<PurchaseHistory> purchaseHistory = Collections.emptyList();
  public User(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }
}