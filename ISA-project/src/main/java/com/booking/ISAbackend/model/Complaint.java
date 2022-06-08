package com.booking.ISAbackend.model;

import javax.persistence.*;

@Entity
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "my_user_id")
	private Client client;

	@Column(nullable = false)
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

	private boolean deleted;

	public Complaint() {}

	public Complaint(String text, Reservation reservation, Client client, boolean deleted) {
		this.text = text;
		this.reservation = reservation;
		this.client = client;
		this.deleted = deleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public boolean isDeleted() {return deleted;}

	public void setDeleted(boolean deleted) { this.deleted = deleted; }

	public Client getClient() {return client;}
}
