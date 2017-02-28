package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
	public Person findByUsername(String username);
	
	public Person findByEmail(String email);
	
	public Person findByEmailAndPassword(String email, String password);
	
}
