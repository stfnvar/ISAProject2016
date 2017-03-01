package com.restaurant.service;

import java.util.ArrayList;

import com.restaurant.model.CookType;
import com.restaurant.model.OrderedDrink;
import com.restaurant.model.OrderedMeal;

public interface OrderedMealService {
	
	public void updateAcceptedMeal(long id);
	
	public void updateMadeMeal(long id);
	
	public OrderedMeal findOneOrderedMeal(long id);
	
	public OrderedMeal save(OrderedMeal orderedMeal);

	public ArrayList<OrderedMeal> findByOrderId(Long id);

	public void updateQuantity(int quantity, long id);
	
	public void updateMealName(long idMeal, long id);
	
	public ArrayList<OrderedMeal> findAllOrderedMeals(Long id, CookType typeC);

}
