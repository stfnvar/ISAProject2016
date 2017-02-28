package com.restaurant.service;

import com.restaurant.model.Person;

public interface PersonService {
	
	Person findOneByUsername(String username);
	
	Person findOneByEmail(String email);
	
	Person findOneByEmailAndPassword(String email, String password);
	
	Person save(Person person);
	
	Person findOne(Long id);
	
}
