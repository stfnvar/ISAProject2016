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
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public RestaurantSegment getRestaurantSegment() {
		return restaurantSegment;
	}


	public void setRestaurantSegment(RestaurantSegment restaurantSegment) {
		this.restaurantSegment = restaurantSegment;
	}


	@ManyToOne(optional = false)
	private Restaurant restaurant;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY, targetEntity=Order.class, mappedBy = "table")
	private Set<Order> order;

	
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true,mappedBy="tables",targetEntity=Reservation.class)
    private Set<Reservation> reservations;

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	} 
	
}
