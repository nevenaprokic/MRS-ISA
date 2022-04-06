package com.booking.ISAbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MyUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String phoneNumber;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private Boolean deleted;

//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private Role role;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToOne(mappedBy = "myUser")
	private DeleteRequest deleteRequest;
}
