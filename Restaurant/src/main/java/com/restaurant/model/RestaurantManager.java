package com.restaurant.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class RestaurantManager extends Person {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantManager")
	private Set<Offer> offers;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantManager")
	private Set<WantedItems> wantedItems;
	
	@ManyToOne(optional = true)
	private Restaurant restaurant;
	
	public RestaurantManager(){

	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
}
