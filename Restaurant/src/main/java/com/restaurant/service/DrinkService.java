package com.restaurant.service;

import java.util.List;
import java.util.Set;

import com.restaurant.model.Drink;

public interface DrinkService {
	
	Drink findByName(String name, Long id);
	
	Set<Drink> findByRestaurant(Long id);
	
}
