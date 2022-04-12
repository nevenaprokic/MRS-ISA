package com.booking.ISAbackend.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Owner extends MyUser {

	@OneToMany(fetch = FetchType.LAZY)
	private List<Transaction> transaction;

	@Enumerated(EnumType.STRING)
	private OwnerCategory ownerCategory;

	public OwnerCategory getOwnerCategory(){
		return ownerCategory;
	}
}