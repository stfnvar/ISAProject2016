package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Meal {
	
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
	private Menu menu;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "rating")
	//private Set<Rating> ratings;
	
	@ManyToOne(optional = true)
	private Order order;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meal")
	private Set<Rating> ratings;
	
	@ManyToOne(optional = false)
	private Drink drink;
	
	public Meal() {
		super();
	}
	
	public Meal(String name, Menu menu) {
		super();
		this.name = name;
		this.menu = menu;
	}
	
}
