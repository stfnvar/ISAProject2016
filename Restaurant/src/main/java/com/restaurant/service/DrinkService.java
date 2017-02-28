package com.restaurant.service;

import java.util.List;

import com.restaurant.model.Drink;

public interface DrinkService {
	
	Drink findByName(String name, Long id);
	
	
}
