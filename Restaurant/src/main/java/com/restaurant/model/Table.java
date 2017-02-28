package com.restaurant.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@javax.persistence.Table(name="TABLE_TABLE")
public class Table {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional = false)
	private RestaurantSegment restaurantSegment;
	
	@ManyToOne(optional = false)
	private Restaurant restaurant;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity=Order.class, mappedBy = "table")
	private Set<Order> order;

	
    @OneToMany(mappedBy="tables",targetEntity=Reservation.class)
    private Set<Reservation> reservations; 
	
}
