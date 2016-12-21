package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class RestaurantSegment {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String type;
	
	@ManyToOne(optional = false)
	private Restaurant restaurant;
	
	
	//@OneToMany(fetch = FetchType.LAZY, targetEntity=Table.class,  mappedBy = "restaurantSegment")
	//private Set<Table> tables;

	
	//@OneToMany(fetch = FetchType.LAZY, targetEntity=Reservation.class, mappedBy="restaurantSegment")
	//private Set<Reservation> reservations;

	
	public RestaurantSegment() {
		super();
	}


	
}
