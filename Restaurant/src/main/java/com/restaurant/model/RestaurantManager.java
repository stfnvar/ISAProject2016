package com.restaurant.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class RestaurantManager extends Person {

	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantManager")
	//private Set<Restaurant> Restaurants;
	

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantManager")
	private Set<Offer> offers;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantManager")
	private Set<WantedItems> wantedItems;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantManager")
	private Set<Waiter> waiter;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "pera_ID", referencedColumnName = "ID")
	private Restaurant restaurant;
	
	
	

	public RestaurantManager(){}
	
	
}
