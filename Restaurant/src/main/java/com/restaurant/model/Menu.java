package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Menu {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Restaurant restaurant;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meal")
	private Set<Meal> meals;

	public Menu() {
		super();
	}
	
	public Menu(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}
	
}
