package com.restaurant.model;


import javax.persistence.Column;

public abstract class Worker extends Person {
	
	//polje lista porudzbina (mozda)
	
	//raspored rada
	
	@Column(nullable = false)
	private int shift;
	
	@Column(nullable = false)
	private int workingHours;
	
	@Column(nullable = false)
	private RestaurantManager restaurantManager;
	
	public Worker(String username, String password, String name, String surname, String email) {
		super(username, password, name, surname, email);
		// TODO Auto-generated constructor stub
	}

}
