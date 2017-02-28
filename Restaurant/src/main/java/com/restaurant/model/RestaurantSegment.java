package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class RestaurantSegment {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String typeOf;
	
	@ManyToOne(optional = false)
	@JsonBackReference
	private Restaurant restaurant;
	
	//@OneToMany(fetch = FetchType.LAZY, targetEntity=Table.class,  mappedBy = "restaurantSegment")
	//private Set<Table> tables;
	
	//@OneToMany(fetch = FetchType.LAZY, targetEntity=Reservation.class, mappedBy="restaurantSegment")
	//private Set<Reservation> reservations;

	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	public RestaurantSegment() {
		super();
	}

	
	public String getName() {
		return name;
	}


	public String getTypeOf() {
		return typeOf;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Restaurant getRestaurant() {
		return restaurant;
	}
}
