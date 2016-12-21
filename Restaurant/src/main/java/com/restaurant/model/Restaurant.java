package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity=RestaurantManager.class, mappedBy = "restaurant")
	private Set<RestaurantManager>  restaurantManager;
	
	@OneToOne(fetch = FetchType.LAZY,targetEntity=Menu.class, mappedBy="restaurant")
	private Menu menu;

	
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity=DrinkCard.class, mappedBy = "restaurant")
	private Set<DrinkCard> drinkCards;
	

	@OneToMany(fetch = FetchType.LAZY, targetEntity=Table.class, mappedBy = "restaurant")
	private Set<Table> tables;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
	private Set<Rating> ratings;
	
	
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
	//private Set<Reservation> reservations;
	

	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantSegment")
	//private Set<RestaurantSegment> restaurantSegments;
	
	public Restaurant() {
		super();
	}
	
	
	
}
