package com.restaurant.model;

import javax.persistence.Column;

public class Cook extends Worker{
	
	@Column(nullable = false)
	private String uloga;
	
	public Cook(String username, String password, String name, String surname, String email) {
		super(username, password, name, surname, email);
		// TODO Auto-generated constructor stub
	}

}
