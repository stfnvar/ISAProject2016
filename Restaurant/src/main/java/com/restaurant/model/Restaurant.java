package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private Menu menu;

	@Column(nullable = false)
	private DrinkCard drinkCard;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rating")
	private Set<Rating> ratings;
	
	//@Column - konfiguracija sedenja
	
	@Column(nullable = false)
	private RestaurantManager restaurantManager;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantSegment")
	private Set<RestaurantSegment> restaurantSegments;
	
	public Restaurant() {
		super();
	}
	
	public Restaurant(String name, String description, RestaurantManager restaurantManager) {
		super();
		this.name = name;
		this.description = description;
		this.restaurantManager = restaurantManager;
	}
	
}
