package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String firstName;
	private String lastName;
	private String password;
	private String phoneNumber;
	private String email;
	private Boolean deleted;
	private Address address;
	
	@OneToOne
	private DeleteRequest deleteRequest;
}
