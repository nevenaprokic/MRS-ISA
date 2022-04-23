package com.booking.ISAbackend.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DeleteRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	
	@OneToOne
	private MyUser myUser;

	public DeleteRequest(){}

	public DeleteRequest(MyUser myUser) {
		this.myUser = myUser;
	}
}
