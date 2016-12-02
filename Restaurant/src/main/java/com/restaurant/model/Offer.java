package com.restaurant.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Offer {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private Date deliveryDeadline;
	
	@Column(nullable = false)
	private int warranty;
	
	@Column(nullable = false)
	private RestaurantManager restaurantManager;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grocery")
	private Set<Grocery> groceries;

	public Offer() {
		super();
	}

	public Offer(double price, Date deliveryDeadline, int warranty, RestaurantManager restaurantManager,
			Set<Grocery> groceries) {
		super();
		this.price = price;
		this.deliveryDeadline = deliveryDeadline;
		this.warranty = warranty;
		this.restaurantManager = restaurantManager;
		this.groceries = groceries;
	}
	
}
