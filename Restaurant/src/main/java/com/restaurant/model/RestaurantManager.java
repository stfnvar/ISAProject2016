package com.restaurant.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class RestaurantManager extends Person {

	@OneToMany(cascade=CascadeType.ALL,targetEntity=Offer.class, orphanRemoval=true, fetch = FetchType.LAZY, mappedBy = "restaurantManager")
	private Set<Offer> offers;
	
	@OneToMany(cascade=CascadeType.ALL, targetEntity=WantedItems.class, orphanRemoval=true, fetch = FetchType.LAZY, mappedBy = "restaurantManager")
	private Set<WantedItems> wantedItems;
	
	@ManyToOne(optional = false)
	private Restaurant restaurant;
	
	public RestaurantManager(){

	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
}
