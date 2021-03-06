package com.restaurant.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Meal {
	
	public CookType getTypeM() {
		return typeM;
	}

	public void setTypeM(CookType typeM) {
		this.typeM = typeM;
	}

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private CookType typeM;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meal")
	private Set<OrderedMeal> orderedMeals;

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
	private Menu menu;
	
	@ManyToOne(optional = true)
	private Order order;
	
	public Long getId() {
		return id;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, targetEntity=Rating.class, orphanRemoval=true, mappedBy = "meal")
	private Set<Rating> ratings;
	
	public Meal() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public Menu getMenu() {
		return menu;
	}
}
