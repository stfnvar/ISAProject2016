package com.restaurant.model;

import javax.persistence.Column;

public class Administrator extends Person{
	
	
	@Column(nullable = false)
	private boolean supreme;
	
	public Administrator(String username, String password, String name, String surname, String email) {
		super(username, password, name, surname, email);
		// TODO Auto-generated constructor stub
	}
	
}
