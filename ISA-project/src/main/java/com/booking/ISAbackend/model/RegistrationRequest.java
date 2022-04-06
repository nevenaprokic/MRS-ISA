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
}
