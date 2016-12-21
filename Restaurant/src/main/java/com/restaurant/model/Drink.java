package com.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Drink {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private double price;
	
	@ManyToOne(optional = false)
	private DrinkCard drinkCard;
	
	@ManyToOne(optional = false)
	private Order order;
	
	@ManyToOne(optional = false)
	private Offer offer;
	
	@ManyToOne(optional = true)
	private WantedItems wantedItems;
	
	

	public Drink() {
		super();
	}

	
}
