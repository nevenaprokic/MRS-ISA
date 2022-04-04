package model;

import java.util.ArrayList;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private ArrayList<String> photo;
	private Integer numberOfPerson;
	private String rulesOfConduct;
	private Map<String, Double> additionalServices;
	private String cancellationConditions;
	private Boolean deleted;
	private Address address;
	
	@OneToMany(mappedBy = "quick_reservation", fetch = FetchType.LAZY)
	private ArrayList<QuickReservation> quickReservations;
	@OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY)
	private ArrayList<Reservation> reservations;

	@ManyToMany(mappedBy = "subscribedOffers")
	private ArrayList<User> subscribedUsers;
}
