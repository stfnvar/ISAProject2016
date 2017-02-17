package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
	public Person findByUsername(String username);
	
}
