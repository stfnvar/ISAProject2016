package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Person;
import com.restaurant.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public Person findOneByUsername(String username) {
		return personRepository.findByUsername(username);
	}

	@Override
	public Person save(Person person) {
		return personRepository.save(person);
	}
	
}
