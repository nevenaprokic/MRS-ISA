package com.booking.ISAbackend.model;
import javax.persistence.*;
import java.util.Optional;

@Entity
public class ReservationReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Boolean penalOption;

	@Column(nullable = false)
	private Boolean automaticallyPenal;

	private String comment;
	@OneToOne
	private Reservation reservation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;

	public ReservationReport(Boolean penalOption, Boolean automaticallyPenal, String comment, Reservation reservation, Client client) {
		this.penalOption = penalOption;
		this.automaticallyPenal = automaticallyPenal;
		this.comment = comment;
		this.reservation = reservation;
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

	public Boolean getPenalOption() {
		return penalOption;
	}

	public Boolean getAutomaticallyPenal() {
		return automaticallyPenal;
	}

	public String getComment() {
		return comment;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public Client getClient() {
		return client;
	}
}
