package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

public class RestaurantManager extends Person {

	@Column(nullable = false)
	private Restaurant restaurant;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grocery")
	private Set<Grocery> groceries;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "worker")
	private Set<Worker> workers;
	
	public RestaurantManager(String username, String password, String name, String surname, String email, Restaurant restaurant) {
		super(username, password, name, surname, email);
		this.restaurant = restaurant;
	}
	
}
