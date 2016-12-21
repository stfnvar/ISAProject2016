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
	
	
	
	@Column(nullable = false)
	private boolean currentlyReserved;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
	//private Set<Reservation> reservation;
	
	@ManyToOne(optional = false)
	private RestaurantSegment restaurantSegment;
	
	@ManyToOne(optional = false)
	private Restaurant restaurant;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "table")
	private Set<Order> order;
	/*
    @OneToOne(optional=false,cascade=CascadeType.ALL, 
    	       mappedBy="order",targetEntity=Invoice.class)
    	       private Ord invoice; 
    */
	
    @OneToOne(optional=false)
    @JoinColumn(name = "RESERVATION_ID") 
    private Reservation reservation; 
	
}
