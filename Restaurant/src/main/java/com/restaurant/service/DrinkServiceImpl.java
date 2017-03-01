package com.restaurant.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Drink;
import com.restaurant.repository.DrinkRepository;

@Service
public class DrinkServiceImpl implements DrinkService {
	
	@Autowired
	DrinkRepository drinkRepository;

	@Override
	public Drink findByName(String name, Long id) {
		// TODO Auto-generated method stub
		return drinkRepository.findByName(name, id);
	}

	@Override
	public Set<Drink> findByRestaurant(Long id) {
		// TODO Auto-generated method stub
		return drinkRepository.findByRestaurant(id);
	}




}
