package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class WantedItems {

	@Id //signifies the primary key
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "wantedItems")
	private Set<Grocery> groceries;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "wantedItems")
	private Set<Drink> drinks;
	
	@ManyToOne(optional = false)
	private RestaurantManager restaurantManager;
	
	
}
