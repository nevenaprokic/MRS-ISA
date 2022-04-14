package com.booking.ISAbackend.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Owner extends MyUser {

	@OneToMany(fetch = FetchType.LAZY)
	private List<Transaction> transaction;

	@Enumerated(EnumType.STRING)
	private OwnerCategory ownerCategory;

	public Owner(String firstName, String lastName, String password, String phoneNumber, String email, Boolean deleted, Role role, Address address, int ownerCategory ) {
		super(firstName, lastName, password, phoneNumber, email, deleted, role, address);
		this.ownerCategory = OwnerCategory.fromInteger(ownerCategory);
	}

	public Owner() {
		super();
	}

	public OwnerCategory getOwnerCategory(){
		return ownerCategory;
	}

}