package com.booking.ISAbackend.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Client extends MyUser {

	@Enumerated(EnumType.STRING)
	private ClientCategory clientCategory;
	
	private Integer penal;
	
	@ManyToMany
	@JoinTable(name = "subscribe", joinColumns = @JoinColumn(name = "my_user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"))
	private List<Offer> subscribedOffers;
}
