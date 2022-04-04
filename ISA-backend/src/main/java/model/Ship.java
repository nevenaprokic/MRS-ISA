package model;

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
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String type;
	private String size;
	private Integer motorNumber;
	private Integer motorPower;
	private Integer maxSpeed;
	private ArrayList<String> navigationEquipment;
	private String additionalEquipment;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ship_owner_id")
	private ShipOwner shipOwner;
}
