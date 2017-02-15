package com.restaurant.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Menu {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional = false)
	private Restaurant restaurant;
	
	@OneToMany(fetch = FetchType.LAZY,targetEntity=Meal.class, mappedBy = "menu")
	private Set<Meal> meals;

	public Menu() {
		super();
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public Menu(Restaurant restaurant) {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
