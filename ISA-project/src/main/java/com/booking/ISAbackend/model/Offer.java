package com.booking.ISAbackend.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private Double price;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Photo> photos;
	private Integer numberOfPerson;
	private String rulesOfConduct;
	@OneToMany(fetch = FetchType.LAZY)
	private List<AdditionalService> additionalServices;
	private String cancellationConditions;
	private Boolean deleted;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY)
	private List<QuickReservation> quickReservations;
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY)
	private List<Reservation> reservations;

	@ManyToMany(mappedBy = "subscribedOffers")
	private List<Client> subscribedClients;
}
