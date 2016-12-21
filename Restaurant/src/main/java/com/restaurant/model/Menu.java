package com.restaurant.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Menu {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(optional = false)
	private Restaurant restaurant;
	
	@OneToMany(fetch = FetchType.LAZY,targetEntity=Meal.class, mappedBy = "menu")
	private Set<Meal> meals;

	public Menu() {
		super();
	}
	
	public Menu(Restaurant restaurant) {
		super();
		
	}
	
}
