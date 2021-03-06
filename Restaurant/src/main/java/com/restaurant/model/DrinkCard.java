package com.restaurant.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class DrinkCard {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional = false)
	private Restaurant restaurant;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY,targetEntity=Drink.class, mappedBy = "drinkCard")
	private Set<Drink> drinks;
	
	public DrinkCard() {
		super();
	}

	public DrinkCard(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	public Long getId() {
		return id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	
}
