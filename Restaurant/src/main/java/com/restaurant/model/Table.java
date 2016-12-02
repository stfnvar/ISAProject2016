package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Table {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private RestaurantSegment restaurantSegment;
	
	@Column(nullable = false)
	private boolean currentlyReserved;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
	private Set<Reservation> reservation;
	
}
