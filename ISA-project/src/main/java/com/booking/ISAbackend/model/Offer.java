package com.booking.ISAbackend.model;

import com.booking.ISAbackend.client.Client;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private Double price;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Photo> photos;
	private Integer numberOfPerson;
	private String rulesOfConduct;
	@OneToMany(fetch = FetchType.LAZY)
	private List<AdditionalService> additionalServices;
	private String cancellationConditions;
	private Boolean deleted;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY)
	private List<QuickReservation> quickReservations;
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY)
	private List<Reservation> reservations;

	@ManyToMany(mappedBy = "subscribedOffers")
	private List<Client> subscribedClients;

	@Version
	@Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
	private Long version;


	public Offer(String name, String description, Double price, List<Photo> photos, Integer numberOfPerson, String rulesOfConduct, List<AdditionalService> additionalServices, String cancellationConditions, Boolean deleted, Address address, List<QuickReservation> quickReservations, List<Reservation> reservations, List<Client> subscribedClients) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.photos = photos;
		this.numberOfPerson = numberOfPerson;
		this.rulesOfConduct = rulesOfConduct;
		this.additionalServices = additionalServices;
		this.cancellationConditions = cancellationConditions;
		this.deleted = deleted;
		this.address = address;
		this.quickReservations = quickReservations;
		this.reservations = reservations;
		this.subscribedClients = subscribedClients;
	}

	public Offer() {

	}
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	public Integer getId() {return id;}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public Integer getNumberOfPerson() {
		return numberOfPerson;
	}

	public String getRulesOfConduct() {
		return rulesOfConduct;
	}

	public List<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}

	public String getCancellationConditions() {
		return cancellationConditions;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public Address getAddress() {
		return address;
	}

	public List<QuickReservation> getQuickReservations() {
		return quickReservations;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public List<Client> getSubscribedClients() {
		return subscribedClients;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public void setNumberOfPerson(Integer numberOfPerson) {
		this.numberOfPerson = numberOfPerson;
	}

	public void setRulesOfConduct(String rulesOfConduct) {
		this.rulesOfConduct = rulesOfConduct;
	}

	public void setAdditionalServices(List<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public void setCancellationConditions(String cancellationConditions) {
		this.cancellationConditions = cancellationConditions;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setQuickReservations(List<QuickReservation> quickReservations) {
		this.quickReservations = quickReservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void setSubscribedClients(List<Client> subscribedClients) {
		this.subscribedClients = subscribedClients;
	}
}
