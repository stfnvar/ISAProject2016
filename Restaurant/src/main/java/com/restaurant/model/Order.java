package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Order {
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	//@Column(nullable = false)
	//private Table table;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "drink")
	private Set<Drink> drinks;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meal")
	private Set<Meal> meals;
	
}
