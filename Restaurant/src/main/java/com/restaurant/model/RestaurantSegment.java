package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RestaurantSegment {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private Restaurant restaurant;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "table")
	private Set<Table> tables;
	
	public RestaurantSegment() {
		super();
	}

	public RestaurantSegment(String name, String type, Restaurant restaurant) {
		super();
		this.name = name;
		this.type = type;
		this.restaurant = restaurant;
	}
	
}
