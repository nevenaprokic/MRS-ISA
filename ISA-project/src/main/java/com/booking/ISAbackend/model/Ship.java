package com.booking.ISAbackend.model;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ship extends Offer{

	private String type;
	private String size;
	private Integer motorNumber;
	private Integer motorPower;
	private Integer maxSpeed;
	private String navigationEquipment;
	private String additionalEquipment;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ship_owner_id")
	private ShipOwner shipOwner;

	public String getType() {
		return type;
	}

	public String getSize() {
		return size;
	}

	public Integer getMotorNumber() {
		return motorNumber;
	}

	public Integer getMotorPower() {
		return motorPower;
	}

	public Integer getMaxSpeed() {
		return maxSpeed;
	}

	public String getNavigationEquipment() {
		return navigationEquipment;
	}

	public String getAdditionalEquipment() {
		return additionalEquipment;
	}

	public ShipOwner getShipOwner() {
		return shipOwner;
	}
}
