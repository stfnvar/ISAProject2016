package com.restaurant.model;

import java.util.Set;

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
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity=Meal.class, mappedBy = "drink")
	private Set<Meal> meals;
	
	public DrinkCard() {
		super();
	}

	public DrinkCard(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}
}
