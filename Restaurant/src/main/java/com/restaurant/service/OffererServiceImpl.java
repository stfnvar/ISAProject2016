package com.restaurant.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Offerer;
import com.restaurant.model.Person;
import com.restaurant.repository.OffererRepository;
import com.restaurant.repository.PersonRepository;
@Service
public class OffererServiceImpl implements OffererService{
	
	
	@Autowired 
	OffererRepository offererRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	
	@Override
	public void removeOfferer(Long id) {
		offererRepository.delete(id);
	}

	@Override
	public void addOfferer(Offerer o) {
		offererRepository.save(o);
	}

	@Override
	public List<Offerer> getOfferers() {
		return offererRepository.findAll();
	}

	@Override
	public List<Offerer> getOfferersByRestaurantId(Long id) {
		return offererRepository.findAll();
	}

	@Override
	public Offerer findOne(Long id) {
		return offererRepository.findOne(id);
	}
	
	@Override
	public Person update(String name, String surname, Long id) {
		personRepository.updateForTypeRestaurantManager(name, surname, id);
		return personRepository.findOne(id);
	}

	@Override
	public Person changePassword(Long id, String password) {
		personRepository.changePasswordForTypeRestaurantManager(id, password);
		return personRepository.findOne(id);
	}

	@Override
	public void changeFirstTime(boolean b, Long id) {
		offererRepository.setFirstTime(b, id);
	}

}
