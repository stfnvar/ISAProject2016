package com.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Grocery {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int quantity;
	
	@Column(nullable = false)
	private double price;
	
	@ManyToOne(optional = false)
	private RestaurantManager restaurantManager;
	
	@ManyToOne(optional = true)
	private WantedItems wantedItems;
	
	
	@ManyToOne(optional = false)
	private Offer offer;

	public Grocery() {
		super();
	}

	public Grocery(String name, int quantity, int price) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
}
