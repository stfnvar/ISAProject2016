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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class RestaurantSegment {
	

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String typeOf;
	
	@ManyToOne(optional = false)
	@JsonBackReference
	private Restaurant restaurant;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY, targetEntity=Table.class,  mappedBy = "restaurantSegment")
	private Set<Table> tables;
	
	//@OneToMany(fetch = FetchType.LAZY, targetEntity=Reservation.class, mappedBy="restaurantSegment")
	//private Set<Reservation> reservations;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true,targetEntity=WorkingSchedule.class, mappedBy = "segment")
	private Set<WorkingSchedule> workingSchedules;
	
	public RestaurantSegment() {
		super();
	}

	
	public Long getId() {
		return id;
	}



	public String getName() {
		return name;
	}


	public String getTypeOf() {
		return typeOf;
	}


	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setId(long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setTypeOf(String typeOf) {
		this.typeOf = typeOf;
	}


	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	public void setWorkingSchedules(Set<WorkingSchedule> workingSchedules) {
		this.workingSchedules = workingSchedules;
	}
	
	

}
