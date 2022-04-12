package com.booking.ISAbackend.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

@Entity
public class QuickReservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private LocalDate startDate;
	@Column(nullable = false)
	private LocalDate endDateAction;
	@Column(nullable = false)
	private LocalDate startDateAction;
	@Column(nullable = false)
	private LocalDate endDate;
	@OneToMany(fetch = FetchType.LAZY)
	private List<AdditionalService> additionalServices;
	@Column(nullable = false)
	private Double price;
	@Column(nullable = false)
	private Integer numberOfPerson;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "offer_id")
	private Offer offer;
}
