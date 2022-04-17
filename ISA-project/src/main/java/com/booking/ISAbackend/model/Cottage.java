package com.booking.ISAbackend.model;

import javax.persistence.*;

@Entity
public class Cottage extends Offer{

	@Column(nullable = false)
	private Integer roomNumber;

	@Column(nullable = false)
	private Integer bedNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "my_user_id")
	private CottageOwner cottageOwner;

	public Integer getRoomNumber() {return roomNumber;}
	public  Integer getBedNumber() {return  bedNumber;}
}
