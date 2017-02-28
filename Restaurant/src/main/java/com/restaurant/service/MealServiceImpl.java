package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Meal;
import com.restaurant.repository.MealRepository;

@Service
public class MealServiceImpl implements MealService {
	
	@Autowired
	MealRepository mealRepository;

	@Override
	public Meal findByName(String name, Long id) {
		// TODO Auto-generated method stub
		return mealRepository.findByName(name, id);
	}


}
