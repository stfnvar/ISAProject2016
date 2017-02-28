package com.restaurant.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Restaurant implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity=RestaurantManager.class, mappedBy="restaurant")
	private Set<RestaurantManager> restaurantManagers;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity=RestaurantManager.class, mappedBy="restaurant")
	private Set<Worker> workers;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity=Menu.class, mappedBy="restaurant")
	private Set<Menu> menus;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity=DrinkCard.class, mappedBy = "restaurant")
	private Set<DrinkCard> drinkCards;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity=Table.class, mappedBy = "restaurant")
	private Set<Table> tables;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
	private Set<Rating> ratings;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", targetEntity = RestaurantSegment.class)
	private Set<RestaurantSegment> restaurantSegments;
	
	public Restaurant() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Set<RestaurantSegment> getRestaurantSegments() {
		return restaurantSegments;
	}
	
}
