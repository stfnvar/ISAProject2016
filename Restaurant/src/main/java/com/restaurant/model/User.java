package com.restaurant.model;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

public class User extends Person{
		
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
		private Set<User> friends;
		
		public User(String username, String password, String name, String surname, String email) {
			super(username, password, name, surname, email);
			// TODO Auto-generated constructor stub
		}
}
