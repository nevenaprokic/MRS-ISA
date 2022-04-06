package com.booking.ISAbackend.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Instructor extends Owner{

	@Column(nullable = false)
	private String biography;
	
	@OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
	private List<Adventure> adventures;
}