package com.booking.ISAbackend.model;
import javax.persistence.*;

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
}
