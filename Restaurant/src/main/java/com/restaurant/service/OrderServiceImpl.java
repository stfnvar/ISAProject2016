package com.restaurant.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Order;
import com.restaurant.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order save(Order order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order);
	}

	@Override
	public ArrayList<Order> findAllOrders(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findAll(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		orderRepository.delete(id);
	}

}
