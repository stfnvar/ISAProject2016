package com.restaurant.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.CookType;
import com.restaurant.model.OrderedMeal;
import com.restaurant.repository.OrderedMealRepository;

@Service
public class OrderedMealServiceImpl implements OrderedMealService {

	@Autowired
	OrderedMealRepository orderedMealRepository;
	
	@Override
	public OrderedMeal save(OrderedMeal orderedMeal) {
		// TODO Auto-generated method stub
		return orderedMealRepository.save(orderedMeal);
	}

	@Override
	public ArrayList<OrderedMeal> findByOrderId(Long id) {
		// TODO Auto-generated method stub
		return orderedMealRepository.findByOrderId(id);
	}

	@Override
	public void updateQuantity(int quantity, long id) {
		// TODO Auto-generated method stub
		orderedMealRepository.updateQuantity(quantity, id);
	}

	@Override
	public void updateMealName(long idMeal, long id) {
		// TODO Auto-generated method stub
		orderedMealRepository.updateMealName(idMeal, id);
	}

	@Override
	public ArrayList<OrderedMeal> findAllOrderedMeals(Long id, CookType typeC) {
		// TODO Auto-generated method stub
		return orderedMealRepository.findAll(id, typeC);
	}

	@Override
	public void updateAcceptedMeal(long id) {
		// TODO Auto-generated method stub
		orderedMealRepository.updateAcceptedMeal(id);
	}

	@Override
	public OrderedMeal findOneOrderedMeal(long id) {
		// TODO Auto-generated method stub
		return orderedMealRepository.findOne(id);
	}

	@Override
	public void updateMadeMeal(long id) {
		// TODO Auto-generated method stub
		orderedMealRepository.updateMadeMeal(id);
	}

}
