package com.restaurant.service;

import com.restaurant.model.Person;

public interface PersonService {
	
	Person findOneByUsername(String username);
	
	Person save(Person person);
	
}
