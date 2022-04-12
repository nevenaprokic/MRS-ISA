package com.booking.ISAbackend.model;

import com.booking.ISAbackend.client.Client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private LocalDate endDate;

	@OneToMany(fetch = FetchType.LAZY)
	private List<AdditionalService> additionalServices;

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false)
	private Integer numberOfPerson;

	@Column(nullable = false)
	private Boolean deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "offer_id")
	private Offer offer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "my_user_id")
	private Client client;
	@OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY)
	private List<Mark> marks;
	@OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY)
	private List<Complaint> complaints;
	@OneToOne
	private ReservationReport report;
}
