package com.restaurant.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@javax.persistence.Table(name="ORDER_order")
public class Order {
	
	@OneToMany(cascade=CascadeType.ALL, targetEntity=OrderedDrink.class, orphanRemoval=true, fetch = FetchType.LAZY, mappedBy = "order")
	private Set<OrderedDrink> orderedDrinks;
	
	@OneToMany(cascade=CascadeType.ALL, targetEntity=OrderedDrink.class, orphanRemoval=true, fetch = FetchType.LAZY, mappedBy = "order")
	private Set<OrderedMeal> orderedMeals;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private Set<Bill> bills;
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional = false)
	private Table table;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY, targetEntity=Drink.class, mappedBy = "order")
	private Set<Drink> drinks;
	
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY, targetEntity=Meal.class, mappedBy = "order")
	private Set<Meal> meals;
	
	public Long getId() {
		return id;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	
	public Order(){}
	
}
