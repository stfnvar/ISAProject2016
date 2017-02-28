package com.restaurant.service;

import com.restaurant.model.Meal;

public interface MealService {
	
	public Meal findByName(String name, Long id);

}
