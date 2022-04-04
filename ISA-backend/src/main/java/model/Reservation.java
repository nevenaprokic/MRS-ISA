package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate startDate;
	private LocalDate endDate;
	private Map<String, Double> additionalServices;
	private Double price;
	private Integer numberOfPerson;
	private Boolean deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "offer_id")
	private Offer offer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(mappedBy = "mark", fetch = FetchType.LAZY)
	private ArrayList<Mark>marks;
	@OneToMany(mappedBy = "complain", fetch = FetchType.LAZY)
	private ArrayList<Complaint>complaints;
	@OneToOne
	private ReservationReport report;
}
