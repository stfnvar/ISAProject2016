package com.restaurant.service;

import java.util.ArrayList;

import com.restaurant.model.OrderedDrink;

public interface OrderedDrinkService {
	
	ArrayList<OrderedDrink> findAllOrderedDrinks(Long id);
	
	public void updateReady(long id);
	
	public OrderedDrink findOneOrderedDrink(long id);

	public OrderedDrink save(OrderedDrink orderedDrink);
	
	public ArrayList<OrderedDrink> findByOrderId(Long id);
	
	public void updateQuantity(int quantity, long id);
	
	public void updateDrinkName(long idDrink, long id);
	

}
