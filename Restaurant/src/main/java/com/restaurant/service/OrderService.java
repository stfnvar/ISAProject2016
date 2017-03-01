package com.restaurant.service;

import java.util.ArrayList;

import com.restaurant.model.Order;

public interface OrderService {

	public Order save(Order order);
	
	public ArrayList<Order> findAllOrders(Long id);

	public void delete(Long id);
	
	

}
