package com.restaurant.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
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
	
	@ManyToOne(optional = false)
	private RestaurantManager restaurantManager;
	
	@OneToMany(cascade=CascadeType.ALL, targetEntity=Grocery.class, orphanRemoval=true, fetch = FetchType.LAZY, mappedBy = "offer")
	private Set<Grocery> groceries;
	
	@OneToMany(cascade=CascadeType.ALL, targetEntity=Drink.class, orphanRemoval=true, fetch = FetchType.LAZY, mappedBy = "offer")
	private Set<Drink> drinks;
	
	@ManyToOne(optional = false)
	private Offerer offerer;

	public Offer() {
		super();
	}


}
