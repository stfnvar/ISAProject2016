package com.restaurant.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Waiter extends Worker {
	
	public Waiter() {}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "waiter")
	private Set<Rating> ratings;

}
