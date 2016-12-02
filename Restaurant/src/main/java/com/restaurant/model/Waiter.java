package com.restaurant.model;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

public class Waiter extends Worker{
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rating")
	private Set<Rating> ratings;
	
	public Waiter(String username, String password, String name, String surname, String email) {
		super(username, password, name, surname, email);
		// TODO Auto-generated constructor stub
	}
	
}
