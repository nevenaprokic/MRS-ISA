package com.booking.ISAbackend.model;
import javax.persistence.*;

@Entity
public class RegistrationRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String personType;

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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	public RegistrationRequest(String description, String personType, String firstName, String lastName, String password, String phoneNumber, String email, Boolean deleted, Address address) {
		this.description = description;
		this.personType = personType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.deleted = deleted;
		this.address = address;
	}

	public RegistrationRequest() {

	}
}
