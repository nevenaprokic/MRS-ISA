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

	public Reservation(Integer id, LocalDate startDate, LocalDate endDate, List<AdditionalService> additionalServices, Double price, Integer numberOfPerson, Boolean deleted, Offer offer, Client client, List<Mark> marks, List<Complaint> complaints, ReservationReport report) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.additionalServices = additionalServices;
		this.price = price;
		this.numberOfPerson = numberOfPerson;
		this.deleted = deleted;
		this.offer = offer;
		this.client = client;
		this.marks = marks;
		this.complaints = complaints;
		this.report = report;
	}
	public Reservation(){}

	public Integer getId() {
		return id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public List<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getNumberOfPerson() {
		return numberOfPerson;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public Offer getOffer() {
		return offer;
	}

	public Client getClient() {
		return client;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public ReservationReport getReport() {
		return report;
	}
}
