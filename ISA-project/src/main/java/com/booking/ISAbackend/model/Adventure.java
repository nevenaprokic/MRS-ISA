package com.booking.ISAbackend.model;

import javax.persistence.*;

@Entity
public class Adventure extends Offer{

	@Column(nullable = false)
	private String additionalEquipment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "my_user_id")
	private Instructor instructor;
}
