package com.restaurant.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Waiter extends Worker {
	
	public Waiter() {}
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true, targetEntity=Rating.class, mappedBy = "waiter")
	private Set<Rating> ratings;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "waiter")
	private Set<Bill> bills;

}
