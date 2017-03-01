package com.restaurant.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class RestaurantManager extends Person {

	@ManyToOne(optional = false)
	private Restaurant restaurant;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Announcement.class, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "restaurantManager")
	private Set<Announcement> announcements;

	public RestaurantManager() {

	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
}
