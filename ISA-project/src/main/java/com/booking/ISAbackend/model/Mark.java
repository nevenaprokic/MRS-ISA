package com.booking.ISAbackend.model;
import javax.persistence.*;

@Entity
public class Mark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer mark;
	private String comment;
	private Boolean approved;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "my_user_id")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

	public Mark(){}

	public Mark(Integer mark, String comment, Boolean approved, Reservation reservation, Client client) {
		this.mark = mark;
		this.comment = comment;
		this.approved = approved;
		this.reservation = reservation;
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

	public Integer getMark() {
		return mark;
	}

	public String getComment() {
		return comment;
	}

	public Boolean getApproved() {
		return approved;
	}

	public Reservation getReservation() {
		return reservation;
	}

}
