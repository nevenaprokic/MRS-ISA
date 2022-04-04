package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cottage extends Offer{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private Integer roomNumber;
	private Integer bedNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cottage_owner_id")
	private CottageOwner cottageOwner;
}
