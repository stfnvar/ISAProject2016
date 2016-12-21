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
	private double rating;
	
	@Column(nullable = true)
	private int experience;
	

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
