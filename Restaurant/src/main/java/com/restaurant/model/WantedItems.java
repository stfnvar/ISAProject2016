package com.restaurant.model;

import java.util.Set;

import javax.persistence.CascadeType;
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
    private Long Id;
	
	@OneToMany(cascade=CascadeType.ALL, targetEntity=Grocery.class, orphanRemoval=true, fetch = FetchType.LAZY, mappedBy = "wantedItems")
	private Set<Grocery> groceries;
	
	@OneToMany(cascade=CascadeType.ALL,targetEntity=Drink.class, orphanRemoval=true, fetch = FetchType.LAZY, mappedBy = "wantedItems")
	private Set<Drink> drinks;
	
	@ManyToOne(optional = false)
	private RestaurantManager restaurantManager;
	
	
}
