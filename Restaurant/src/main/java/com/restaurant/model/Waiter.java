package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Waiter extends Worker {
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "waiter")
	private Set<Rating> ratings;
	
	
	
	 @ManyToOne(optional=false)
	// @JoinColumn(name="RESTMGMT_ID",referencedColumnName="ID")
	 private RestaurantManager restaurantManager;

}
