package com.restaurant.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Drink {
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "drink")
	private Set<OrderedDrink> orderedDrinks;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private double price;
	
	@ManyToOne(optional = false)
	private DrinkCard drinkCard;
	
	@ManyToOne(optional = true)
	private Order order;
	
	@OneToMany(targetEntity=WantedDrink.class, cascade=CascadeType.ALL, mappedBy="drink", orphanRemoval=true)
	private Set<WantedDrink> wantedDrinks;
	
	public Drink() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}
	
	public DrinkCard getDrinkCard() {
		return drinkCard;
	}

	public void setDrinkCard(DrinkCard drinkCard) {
		this.drinkCard = drinkCard;
	}
	
}
