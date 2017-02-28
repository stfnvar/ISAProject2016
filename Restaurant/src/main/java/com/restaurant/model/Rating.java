package com.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Rating {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Double rating;
	
	@Column(nullable = true)
	private Integer experience;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	@ManyToOne(optional = true)
	private Restaurant restaurant;

	@ManyToOne(optional = true)
	private Meal meal;
	
	@ManyToOne(optional = true)
	private Waiter waiter;

	public Rating() {
		super();
	}

	
	
}
