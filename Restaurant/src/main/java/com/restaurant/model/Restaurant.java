package com.restaurant.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Restaurant implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY, targetEntity = RestaurantManager.class, mappedBy = "restaurant")
	private Set<RestaurantManager> restaurantManagers;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY, targetEntity = Worker.class, mappedBy = "restaurant")
	private Set<Worker> workers;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY, targetEntity = Menu.class, mappedBy = "restaurant")
	private Set<Menu> menus;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY, targetEntity = DrinkCard.class, mappedBy = "restaurant")
	private Set<DrinkCard> drinkCards;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY, targetEntity = Table.class, mappedBy = "restaurant")
	private Set<Table> tables;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY, targetEntity = Invite.class, mappedBy = "rest")
	private Set<Invite> invite;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY, mappedBy = "restaurant", targetEntity=Rating.class)
	private Set<Rating> ratings;

	@OneToMany(cascade=CascadeType.ALL,  orphanRemoval=true, fetch = FetchType.EAGER, mappedBy = "restaurant", targetEntity = RestaurantSegment.class)
	private Set<RestaurantSegment> restaurantSegments;
	
	public Restaurant() {
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

	public Set<RestaurantSegment> getRestaurantSegments() {
		return restaurantSegments;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
