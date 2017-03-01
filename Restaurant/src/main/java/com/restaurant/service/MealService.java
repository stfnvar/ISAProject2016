package com.restaurant.service;

import java.util.Set;

import com.restaurant.model.Meal;

public interface MealService {
	
	public Meal findByName(String name, Long id);

	Set<Meal> findByRestaurant(Long id);
}
