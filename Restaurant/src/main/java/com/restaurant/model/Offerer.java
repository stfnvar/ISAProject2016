package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Offerer extends Person{
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "offerer")
	private Set<Offer> offers;
	
	@Column(nullable = true)
	private String companyName;
	

}
